package haservice.ha_emergency.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import haservice.ha_emergency.dto.AEInfoResponseDto;

public interface EmergencyOperation {
  
  /**
   * This method is to return the top three nearest emergency service provided 
   * by Hospital Authority in Hong Kong from users with latitude and longtitude)
   */
  @PostMapping(value = "/aeservice")
  List<AEInfoResponseDto> getNearestAEInfo(
        @RequestParam String lat, @RequestParam String lon)
        throws JsonProcessingException;
}
