package haservice.ha_emergency.util;

import org.springframework.stereotype.Component;

@Component
public class Geocoding {

  private static final double EARTH_RADIUS_KM = 6371.0;

  public Double getLocDist(String lat1,String lon1, String lat2, String lon2){

    Double lat1Rad = Math.toRadians(Double.parseDouble(lat1));
    Double lon1Rad = Math.toRadians(Double.parseDouble(lon1));
    Double lat2Rad = Math.toRadians(Double.parseDouble(lat2));
    Double lon2Rad = Math.toRadians(Double.parseDouble(lon2));

    Double dLat = lat2Rad - lat1Rad;
    Double dLon = lon2Rad - lon1Rad;

    Double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                Math.pow(Math.sin(dLon / 2), 2);
    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    Double distance = EARTH_RADIUS_KM * c;

    return distance;
  }

}
