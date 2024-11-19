package haservice.ha_emergency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import haservice.ha_emergency.service.HospitalService;
import haservice.ha_emergency.service.EmergencyInfoService;

@Component
// CommandLineRunner
// Run at the beginning of server start
public class AppRunner implements CommandLineRunner{
  
  @Autowired
  private HospitalService hospitalService;

  @Autowired
  private EmergencyInfoService emergencyInfoService;
  
  @Override
  public void run(String... args) throws Exception{
    this.hospitalService.saveHospitals();
    this.emergencyInfoService.saveWaitingTimeToDB();
  }
}
