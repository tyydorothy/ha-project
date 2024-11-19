package haservice.ha_emergency.exception;

import lombok.Getter;

@Getter
public enum ServiceErrorCode {
  INVALID_INPUT_COOR(100,"Lat and Lon must be numeric value"),

  ;

  private int code;
  private String message;

  private ServiceErrorCode(int code,String message){
    this.message = message;
    this.code = code;
  }
}
