package haservice.ha_emergency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HaEmergencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaEmergencyApplication.class, args);
	}

}
