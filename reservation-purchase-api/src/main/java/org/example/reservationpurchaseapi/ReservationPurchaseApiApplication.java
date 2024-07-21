package org.example.reservationpurchaseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ReservationPurchaseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationPurchaseApiApplication.class, args);
	}

}
