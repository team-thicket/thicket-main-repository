package com.example.thicketbatch.service;

import com.example.thicketbatch.dto.request.RequestCreateTicketDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    // JSON 직렬화 및 역직렬화를 수행하는 Jackson 라이브러리의 핵심 클래스
    private final ObjectMapper objectMapper;

    public CompletableFuture<String> send(RequestCreateTicketDto ticketDto) {

        // 객체를 JSON 문자열로 직렬화
        String jsonMessage;
        try {
            jsonMessage = objectMapper.writeValueAsString(ticketDto);
        } catch (JsonProcessingException e) {
            // JSON 직렬화 실패 시
            return CompletableFuture.completedFuture("JSON 직렬화 실패");
        }

        // Kafka에 메시지 전송
        CompletableFuture<SendResult<String, String>> future = CompletableFuture.supplyAsync(() -> {
            try {
                return kafkaTemplate.send("test2", jsonMessage).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // 메시지 전송 결과에 대한 처리 - 비동기
        return future.thenApply(result -> {
            // 메시지 전송 성공 시
            System.out.println("Sent message=[" +
                    "] with offset=[" + result.getRecordMetadata().offset() + "]");
            return "성공";
        }).exceptionally(ex -> {
            // 메시지 전송 실패 시
            System.out.println("Unable to send message= due to : " + ex.getMessage());
            return "실패";
        });
    }
}
