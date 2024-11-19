package haservice.ha_emergency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter // required for JSON serialization by @ResponseBody in Controller
@Builder
@AllArgsConstructor // required for Builder
public class AEInfoResponseDto {
  private String hospName;
  private String hospAddress;
  private String topWait;
}
