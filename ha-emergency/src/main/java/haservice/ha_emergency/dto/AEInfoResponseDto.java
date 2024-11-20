package haservice.ha_emergency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter 
@Builder
@AllArgsConstructor 
public class AEInfoResponseDto {
  private String hospName;
  private String hospAddress;
  private String topWait;
}
