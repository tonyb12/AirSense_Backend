package de.tonyb12.AirSense_Backend;

import de.tonyb12.AirSense_Backend.managers.DiscordMessegingManager;
import de.tonyb12.AirSense_Backend.managers.WeatherCallManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class AirSenseBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(AirSenseBackendApplication.class, args);

	}

}
