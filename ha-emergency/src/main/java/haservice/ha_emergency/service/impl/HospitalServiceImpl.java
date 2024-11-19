package haservice.ha_emergency.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import haservice.ha_emergency.entity.HospitalEntity;
import haservice.ha_emergency.mapper.EntityMapper;
import haservice.ha_emergency.model.Hospital;
import haservice.ha_emergency.repository.HospitalRepository;
import haservice.ha_emergency.service.HospitalService;
import haservice.ha_emergency.util.UrlManager;

@Service
public class HospitalServiceImpl implements HospitalService{
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private UrlManager urlManager;

  @Value("${api.url.endpoint.hospital}")
  private String endpoint;

  @Autowired
  private EntityMapper entityMapper;

  @Autowired
  private HospitalRepository hospitalRepository;

  @Override
  public Hospital[] getHospitals(){
    return this.restTemplate.getForObject(this.urlManager.getUrl(endpoint), Hospital[].class);
  }

  @Override
  public void saveHospitals(){
    List<HospitalEntity> hospitalEntities = Arrays.asList(this.getHospitals()).stream()
    .map(hosp -> entityMapper.map(hosp))
    .collect(Collectors.toList());

    if(hospitalRepository.count()==0)
      this.hospitalRepository.saveAll(hospitalEntities);
  }


}
