package haservice.ha_emergency.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import haservice.ha_emergency.dto.AEInfoResponseDto;
import haservice.ha_emergency.entity.AEWaitTimeEntity;
import haservice.ha_emergency.entity.HospitalEntity;
import haservice.ha_emergency.model.AEWaitTimeUpdate;

// Entity->DTO
@Component
public class DtoMapper {
  public AEInfoResponseDto map(HospitalEntity hospitalEntity, List<AEWaitTimeEntity> aeWaitTimeEntities){
    AEWaitTimeEntity aeWaitTimeEntity = aeWaitTimeEntities.stream()
      .filter(ent -> ent.getHospital().equals(hospitalEntity))
      .findAny()
      .get();
      
    return AEInfoResponseDto.builder()
      .hospName(hospitalEntity.getHospName())
      .hospAddress(hospitalEntity.getAddress())
      .topWait(aeWaitTimeEntity.getTopWait())
      .build();
  }

  public AEInfoResponseDto map(HospitalEntity hospitalEntity, AEWaitTimeUpdate aeWaitTimeUpdate){
    String topWait = aeWaitTimeUpdate.getAeWaitingTime().stream()
      .filter(e -> e.getHospName().equals(hospitalEntity.getHospName()))
      .findFirst()
      .get()
      .getTopWait();
      
    return AEInfoResponseDto.builder()
      .hospName(hospitalEntity.getHospName())
      .hospAddress(hospitalEntity.getAddress())
      .topWait(topWait)
      .build();
  }
}
