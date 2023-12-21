package com.example.thicketgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ThicketGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThicketGatewayApplication.class, args);
    }

}
