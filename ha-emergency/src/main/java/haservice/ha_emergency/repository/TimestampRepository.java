package haservice.ha_emergency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import haservice.ha_emergency.entity.TimestampEntity;

public interface TimestampRepository extends JpaRepository<TimestampEntity,Long> {
  // customize JPA methods
  TimestampEntity findFirstByOrderByIdDesc();

  // need to customise methods here before using in other classes
}
