package haservice.ha_emergency.exception;

import lombok.Getter;

@Getter
public enum SystemErrorCode {
  FAIL ("Fail to start server."),
  ;

  private String message;

  private SystemErrorCode(String message){
    this.message=message;
  }
}
