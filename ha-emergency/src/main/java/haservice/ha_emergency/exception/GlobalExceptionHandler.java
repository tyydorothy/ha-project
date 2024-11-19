package haservice.ha_emergency.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(GeneralException.class)
  public ErrorResponse ServiceExceptionHandler(GeneralException e){
    return ErrorResponse.builder()
      .code(e.getCode())
      .message(e.getMessage())
      .build();
  }

  @ExceptionHandler(Exception.class)
  public ErrorResponse ExceptionHandler(Exception e){
    return ErrorResponse.builder()
    .message(SystemErrorCode.FAIL.getMessage())
    .build();
  }
}
