package haservice.ha_emergency.exception;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException{
  private int code;
  public GeneralException(ServiceErrorCode code){
    super(code.getMessage());

  }
}
