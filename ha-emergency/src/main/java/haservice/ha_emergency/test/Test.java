package haservice.ha_emergency.test;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import haservice.ha_emergency.dto.AEInfoResponseDto;
import haservice.ha_emergency.model.AEWaitTimeUpdate;
import haservice.ha_emergency.model.Hospital;
import haservice.ha_emergency.service.EmergencyInfoService;
import haservice.ha_emergency.service.HospitalService;
import haservice.ha_emergency.util.Geocoding;


// @Controller + @ResponseBody
// @ResponseBody --> convert returned object to JSON format
@RestController
public class Test {

  @Autowired
  private HospitalService hospitalService;

  @Autowired
  private EmergencyInfoService emergencyInfoService;

  @Autowired
  private Geocoding geocoding;

  @GetMapping(value="/test/hospitals")
  public Hospital[] getHospitals(){
    return hospitalService.getHospitals();
  }

  @GetMapping(value = "/test/aeservice")
  public AEWaitTimeUpdate getWaitingTime() throws JsonProcessingException{
    return emergencyInfoService.getWaitingTime();
  }
  
  @GetMapping("/test/latestaeservice")
  public String getLatestAEInfo() throws JsonProcessingException{
    if (emergencyInfoService.getLatestAEInfoFromRedis()==null)
      return emergencyInfoService.getLatestAEInfoFromDB().toString();
    return emergencyInfoService.getLatestAEInfoFromRedis().toString();
  }

  @PostMapping("/test/info")
  public List<AEInfoResponseDto> getClosestAEInfo(@RequestParam String lat, @RequestParam String lon) throws JsonProcessingException {
    return emergencyInfoService.getClosestAEInfo(lat, lon);
}
  
  // @PostMapping(value = "/geo")
  // public String getCoordinates(@RequestParam String address){
  //   return geocoding.getCoordinates(address);
  // }
}
