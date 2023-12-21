package com.example.thicketticket.config;

import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConfig {
    //Trusted Packages 설정 추가
    @Bean
    public JsonDeserializer<RequestCreateTicketDto> jsonDeserializer() {
        JsonDeserializer<RequestCreateTicketDto> deserializer = new JsonDeserializer<>(RequestCreateTicketDto.class);
        deserializer.addTrustedPackages("com.example.thicketticket.dto.request");
        return deserializer;
    }
}