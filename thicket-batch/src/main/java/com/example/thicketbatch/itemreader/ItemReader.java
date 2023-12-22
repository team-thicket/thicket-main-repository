package com.example.thicketbatch.itemreader;

import com.example.thicketbatch.dto.request.RequestCreateTicketDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
//@RequiredArgsConstructor
//public class ItemReader  {
//
//    private final ObjectMapper objectMapper;
//
//    @KafkaListener(topics = "test", groupId = "group-id-1")
//    public void consume(ConsumerRecord<String, String> record) {
//        try {
//            // Kafka 메시지의 값을 RequestCreateTicketDto로 역직렬화
//            RequestCreateTicketDto requestCreateTicketDto = objectMapper.readValue(record.value(), RequestCreateTicketDto.class);
//            // 출력
//            System.out.println("Received message: " + requestCreateTicketDto.toString());
//
//
//        } catch (Exception e) {
//            // 역직렬화나 처리 중에 오류가 발생한 경우 예외 처리
//            System.err.println("Error processing Kafka message: " + e.getMessage());
//            e.printStackTrace();
//            // 예외 처리에 따른 추가 로직 수행
//
//        }
//    }
//
//}