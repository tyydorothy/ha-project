package haservice.ha_emergency.entity;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tTimestamp")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimestampEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "update_id")
  private Long id;

  @Column(name = "ha_timestamp")
  private String haTimestamp;

  @CreationTimestamp (source = SourceType.DB)
  @Column(name="sys_timestamp", columnDefinition = "timestamp with time zone")
  private String sysTimestamp;

  @Builder.Default
  @OneToMany (mappedBy = "updateId")
  private List<AEWaitTimeEntity> hospWaitTimeEntities = new ArrayList<>();
}
