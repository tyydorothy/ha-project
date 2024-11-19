package haservice.ha_emergency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import haservice.ha_emergency.entity.AEWaitTimeEntity;

public interface AEWaitTimeRepository extends JpaRepository<AEWaitTimeEntity,Long>{

}
