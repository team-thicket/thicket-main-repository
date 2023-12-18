package com.example.thicketticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ThicketTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThicketTicketApplication.class, args);
    }

}
