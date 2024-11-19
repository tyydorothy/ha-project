package haservice.ha_emergency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import haservice.ha_emergency.entity.HospitalEntity;

public interface HospitalRepository extends JpaRepository<HospitalEntity,Long>  {
  
}
