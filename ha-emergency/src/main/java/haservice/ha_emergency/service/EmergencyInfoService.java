package haservice.ha_emergency.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import haservice.ha_emergency.dto.AEInfoResponseDto;
import haservice.ha_emergency.entity.AEWaitTimeEntity;
import haservice.ha_emergency.model.AEWaitTimeUpdate;

public interface EmergencyInfoService {

  AEWaitTimeUpdate getWaitingTime() throws JsonProcessingException;

  void saveWaitingTimeToDB() throws JsonProcessingException;

  void saveWaitTimeToRedis() throws JsonProcessingException;

  List<AEWaitTimeEntity> getLatestAEInfoFromDB();

  AEWaitTimeUpdate getLatestAEInfoFromRedis() throws JsonProcessingException;

  List<AEInfoResponseDto> getNearestAEInfo(String lat, String lon) throws JsonProcessingException;

}
