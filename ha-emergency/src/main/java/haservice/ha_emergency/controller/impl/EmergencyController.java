package haservice.ha_emergency.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import haservice.ha_emergency.controller.EmergencyOperation;
import haservice.ha_emergency.dto.AEInfoResponseDto;
import haservice.ha_emergency.service.EmergencyInfoService;

@RestController
public class EmergencyController implements EmergencyOperation {
  @Autowired
  private EmergencyInfoService emergencyInfoService;

  public List<AEInfoResponseDto> getClosestAEInfo(@RequestParam String lat, @RequestParam String lon) throws JsonProcessingException {
      return emergencyInfoService.getClosestAEInfo(lat, lon);
  }
}
