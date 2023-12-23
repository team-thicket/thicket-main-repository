package com.example.thicketeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ThicketEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThicketEurekaServerApplication.class, args);
    }

}
