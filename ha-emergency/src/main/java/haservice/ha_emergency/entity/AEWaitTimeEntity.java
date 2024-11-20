package haservice.ha_emergency.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="tAeWaitTime")

@Builder
@AllArgsConstructor 
@NoArgsConstructor 
@Getter
@Setter
@ToString
public class AEWaitTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "record_id")
  private Long id;
  private String topWait;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hospital")
  private HospitalEntity hospital;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "update_id")
  private TimestampEntity updateId;
}
