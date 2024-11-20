package haservice.ha_emergency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import haservice.ha_emergency.service.EmergencyInfoService;

@Component
public class AppScheduler {
  @Autowired
  private EmergencyInfoService emergencyInfoService;

  @Scheduled (cron = "0 2,17,32,47 * * * *")
  private void saveLatestWaitTime() throws JsonProcessingException{
    emergencyInfoService.saveWaitingTimeToDB();
    emergencyInfoService.saveWaitTimeToRedis();
  }

}
