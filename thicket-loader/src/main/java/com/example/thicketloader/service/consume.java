package com.example.thicketloader.service;

import com.example.thicketloader.dto.RequestCreateTicketDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class consume {
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;



    @KafkaListener(topics = "test", groupId = "group-id-1")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            // Kafka 메시지의 값을 RequestCreateTicketDto로 역직렬화
            RequestCreateTicketDto requestCreateTicketDto = objectMapper.readValue(record.value(), RequestCreateTicketDto.class);
            // consume한 메세지
            System.out.println("Received message: " + requestCreateTicketDto.toString());

        } catch (Exception e) {
            // 역직렬화나 처리 중에 오류가 발생한 경우 예외 처리
            System.err.println("Error processing Kafka message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
