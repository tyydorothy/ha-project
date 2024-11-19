package haservice.ha_emergency.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import haservice.ha_emergency.dto.AEInfoResponseDto;

public interface EmergencyOperation {
  @PostMapping(value = "/aeservice")
  List<AEInfoResponseDto> getClosestAEInfo(@RequestParam String lat, @RequestParam String lon) throws JsonProcessingException;
}
