package haservice.ha_emergency.service;

import haservice.ha_emergency.model.Hospital;

public interface HospitalService {
   Hospital[] getHospitals();
   void saveHospitals();
}
