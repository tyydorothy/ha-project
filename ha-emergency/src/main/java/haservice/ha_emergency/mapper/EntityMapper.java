package haservice.ha_emergency.mapper;

import org.springframework.stereotype.Component;
import haservice.ha_emergency.entity.AEWaitTimeEntity;
import haservice.ha_emergency.entity.HospitalEntity;
import haservice.ha_emergency.entity.TimestampEntity;
import haservice.ha_emergency.model.Hospital;
import haservice.ha_emergency.model.AEWaitTimeUpdate;

// Model->Entity
@Component
public class EntityMapper {
  public HospitalEntity map(Hospital hospital){
    return HospitalEntity.builder()
    .hospName(hospital.getInstitution_eng())
    .aeService(hospital.getWith_AE_service_eng().equals("Yes"))
    .address(hospital.getAddress_eng())
    .cluster(hospital.getCluster_eng())
    .latitude(hospital.getLatitude())
    .longitude(hospital.getLongitude())
    .build();
  }

  public TimestampEntity map(AEWaitTimeUpdate waitTimeUpdate){
    return TimestampEntity.builder()
    .haTimestamp(waitTimeUpdate.getUpdateTime())
    .build();
  }

  public AEWaitTimeEntity map(AEWaitTimeUpdate.AEWaitTime aeWaitTime){
    return AEWaitTimeEntity.builder()
    .topWait(aeWaitTime.getTopWait())
    .build();
  }
  
}
