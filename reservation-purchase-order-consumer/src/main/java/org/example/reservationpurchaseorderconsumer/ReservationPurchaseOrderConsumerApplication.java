package org.example.reservationpurchaseorderconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({
        "org.example.reservationpurchaseapi",
        "org.example.reservationpurchaseorderconsumer"
})
@EnableScheduling
@SpringBootApplication
public class ReservationPurchaseOrderConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationPurchaseOrderConsumerApplication.class, args);
    }

}
