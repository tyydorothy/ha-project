package haservice.ha_emergency.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tHospital")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HospitalEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column (name = "hospital_id")
  private Long id;
  private String hospName;
  private Boolean aeService;
  private String address;
  private String cluster;
  private String latitude;
  private String longitude;

  @Builder.Default
  @OneToMany(mappedBy = "hospital")
  private List<AEWaitTimeEntity> aeWaitTimeEntities = new ArrayList<>();

}
