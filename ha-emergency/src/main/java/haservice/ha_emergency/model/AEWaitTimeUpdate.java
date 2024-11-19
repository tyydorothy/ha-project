package haservice.ha_emergency.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

// This class is designed for Serialization (JSON -> Java Object) ONLY.
// So, only getter is required
@Getter
@ToString
public class AEWaitTimeUpdate{
  @JsonProperty(value = "waitTime") // specify object name if diff from original source
  private List<AEWaitTime> aeWaitingTime = new ArrayList<>();
  private String updateTime;

  @Getter
  @ToString

  public static class AEWaitTime{
    private String hospName;
    private String topWait;
  }

}
