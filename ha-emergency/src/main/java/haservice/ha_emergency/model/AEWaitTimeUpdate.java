package haservice.ha_emergency.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AEWaitTimeUpdate{
  @JsonProperty(value = "waitTime")
  private List<AEWaitTime> aeWaitingTime = new ArrayList<>();
  private String updateTime;

  @Getter
  @ToString
  public static class AEWaitTime{
    private String hospName;
    private String topWait;
  }

}
