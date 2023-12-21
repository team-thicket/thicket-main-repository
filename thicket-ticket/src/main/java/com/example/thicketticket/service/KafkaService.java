package com.example.thicketticket.service;

import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaService {
    public void consume(ConsumerRecord<String, String> record);


}
