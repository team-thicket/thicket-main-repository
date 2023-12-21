package com.example.thicketticket.service;


import com.example.thicketticket.dto.request.RequestCreateTicketDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final ObjectMapper objectMapper;

//    @KafkaListener(topics = "test", groupId = "group-id-1")
//    public void consume(String message) {
//
//        System.out.println("Received message:성공" );
//
//        // 이 메서드 내에서 역직렬화된 객체에 대한 추가 처리를 수행할 수 있습니다.
//    }

    @KafkaListener(topics = "test", groupId = "group-id-1")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            // Kafka 메시지의 값을 RequestCreateTicketDto로 역직렬화
            RequestCreateTicketDto requestCreateTicketDto = objectMapper.readValue(record.value(), RequestCreateTicketDto.class);

            // 출력
            System.out.println("Received message: " + requestCreateTicketDto.toString());

            // 여기서 필요한 추가 처리를 수행
            // 예: 비즈니스 로직 호출, 로깅 등

        } catch (Exception e) {
            // 역직렬화나 처리 중에 오류가 발생한 경우 예외 처리
            System.err.println("Error processing Kafka message: " + e.getMessage());
            e.printStackTrace();
            // 예외 처리에 따른 추가 로직 수행
        }
    }

}
