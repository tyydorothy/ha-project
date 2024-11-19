package haservice.ha_emergency.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class ErrorResponse {
  private int code;
  private String message;

}
