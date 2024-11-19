package haservice.ha_emergency.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import haservice.ha_emergency.dto.AEInfoResponseDto;
import haservice.ha_emergency.entity.AEWaitTimeEntity;
import haservice.ha_emergency.entity.HospitalEntity;
import haservice.ha_emergency.entity.TimestampEntity;
import haservice.ha_emergency.exception.GeneralException;
import haservice.ha_emergency.exception.ServiceErrorCode;
import haservice.ha_emergency.mapper.DtoMapper;
import haservice.ha_emergency.mapper.EntityMapper;
import haservice.ha_emergency.model.AEWaitTimeUpdate;
import haservice.ha_emergency.model.AEWaitTimeUpdate.AEWaitTime;
import haservice.ha_emergency.repository.AEWaitTimeRepository;
import haservice.ha_emergency.repository.HospitalRepository;
import haservice.ha_emergency.repository.TimestampRepository;
import haservice.ha_emergency.service.EmergencyInfoService;
import haservice.ha_emergency.util.Geocoding;
import haservice.ha_emergency.util.UrlManager;


@Service
public class EmergencyInfoServiceImpl implements EmergencyInfoService{
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private TimestampRepository timestampRepository;

  @Autowired
  private AEWaitTimeRepository aeWaitTimeRepository;

  @Autowired
  private HospitalRepository hospitalRepository;

  @Autowired
  private RedisTemplate<String,String> redisTemplate;

  @Autowired
  private UrlManager urlManager;

  @Autowired
  private EntityMapper entityMapper;

  @Autowired
  private DtoMapper dtoMapper;

  @Autowired
  private Geocoding geocoding;

  @Value("${api.url.endpoint.waitingtime}")
  private String endpoint;



  @Override
  public AEWaitTimeUpdate getWaitingTime() throws JsonProcessingException{

    String json = this.restTemplate.getForObject(this.urlManager.getUrl(endpoint), String.class);
    return objectMapper.readValue(json, AEWaitTimeUpdate.class);

    // unable to directly get list of objects by RestTemplate
    // original json is an object, with list of sub-class objects
  }

  @Override
  public void saveWaitingTimeToDB() throws JsonProcessingException{
    List<HospitalEntity> hospitals = hospitalRepository.findAll();

    TimestampEntity aeWaitTimeUpdate = this.entityMapper.map(this.getWaitingTime());

    List<AEWaitTime> aeWaitTime = this.getWaitingTime().getAeWaitingTime();

    timestampRepository.save(aeWaitTimeUpdate); // need to save parent data before saving child one // ERROR: save the transient instance before flushing
    
    for (HospitalEntity hosp:hospitals){

      List<AEWaitTimeEntity> aeWaitTimeEntities = aeWaitTime.stream()
        .filter(aeWT -> aeWT.getHospName().equals(hosp.getHospName()))
        .map(aeWT -> {
          AEWaitTimeEntity aeWaitTimeEntity = this.entityMapper.map(aeWT);
          aeWaitTimeEntity.setHospital(hosp);
          aeWaitTimeEntity.setUpdateId(aeWaitTimeUpdate);
          return aeWaitTimeEntity;
        })
        .collect(Collectors.toList());
      
      aeWaitTimeRepository.saveAll(aeWaitTimeEntities);

    }

  }

  @Override
  public void saveWaitTimeToRedis() throws JsonProcessingException{
    String jsonToWrite = this.objectMapper.writeValueAsString(this.getWaitingTime());
    this.redisTemplate.opsForValue().set("WaitTimeInfo",jsonToWrite,Duration.ofMinutes(15));
  }

  @Override
  public List<AEWaitTimeEntity> getLatestAEInfoFromDB(){
    return aeWaitTimeRepository.findAll().stream()
      .filter(e -> e.getUpdateId().equals(timestampRepository.findFirstByOrderByIdDesc()))
      .collect(Collectors.toList()); 
  }

  @Override
  public AEWaitTimeUpdate getLatestAEInfoFromRedis() throws JsonProcessingException{
    String dataFromRedis = this.redisTemplate.opsForValue().get("WaitTimeInfo");
    if(dataFromRedis==null)
      return null;

    return this.objectMapper.readValue(dataFromRedis,AEWaitTimeUpdate.class);
  }

  @Override
  public List<AEInfoResponseDto> getClosestAEInfo(String lat, String lon) throws JsonProcessingException{

    try{

      Double.parseDouble(lat);
      Double.parseDouble(lon);

    }catch(NumberFormatException e){

      throw new GeneralException(ServiceErrorCode.INVALID_INPUT_COOR);

    }

    List<HospitalEntity> hospitals = hospitalRepository.findAll().stream()
      .filter(e-> e.getAeService())
      .collect(Collectors.toList());

    Map<HospitalEntity,Double> hospDistMap = hospitals.stream()
      .collect(Collectors.toMap(hospEntity -> hospEntity,
                                hospEntity -> geocoding.getLocDist(hospEntity.getLatitude(), hospEntity.getLongitude(), lat, lon)));
    
    AEWaitTimeUpdate aeLatestInfoFromRedis = this.getLatestAEInfoFromRedis();

    if (aeLatestInfoFromRedis==null)
      return hospDistMap.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .limit(3)
        .map(e->this.dtoMapper.map(
          e.getKey()
          ,this.getLatestAEInfoFromDB()))
        .collect(Collectors.toList());

    return hospDistMap.entrySet().stream()
      .sorted(Map.Entry.comparingByValue())
      .limit(3)
      .map(e->this.dtoMapper.map(
        e.getKey()
        ,aeLatestInfoFromRedis))
      .collect(Collectors.toList());
    
  }


}
