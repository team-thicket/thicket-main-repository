package com.example.thicketbatch.job;

import com.example.thicketbatch.StageRepository;
import com.example.thicketbatch.dto.request.RequestCreateTicketDto;
import com.example.thicketbatch.service.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class BatchJob {

    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;
    private final StageRepository stageRepository;
    // 큐에 메시지를 쌓을 Map
    private Map<String, PriorityQueue<RequestCreateTicketDto>> groupedByStageId = new ConcurrentHashMap<>();
    //쓰레드 동적 할당
    ExecutorService executorService = Executors.newCachedThreadPool();



    @KafkaListener(topics = "test", groupId = "group-id-1")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            // Kafka 메시지의 값을 RequestCreateTicketDto로 역직렬화
            RequestCreateTicketDto requestCreateTicketDto = objectMapper.readValue(record.value(), RequestCreateTicketDto.class);
            // 출력
            System.out.println("Received message: " + requestCreateTicketDto.toString());

            // stageId를 기준으로 메시지를 큐에 추가
            addToQueue(requestCreateTicketDto);


        } catch (Exception e) {
            // 역직렬화나 처리 중에 오류가 발생한 경우 예외 처리
            System.err.println("Error processing Kafka message: " + e.getMessage());
            e.printStackTrace();
            // 예외 처리에 따른 추가 로직 수행
        }
    }

    private void addToQueue(RequestCreateTicketDto message) {
        String stageId = message.getStageId();

        groupedByStageId.computeIfAbsent(stageId, k -> new PriorityQueue<>())
                .offer(message);

        System.out.println("Added message to queue. StageId: " + stageId + ", Message: " + message);
    }

    @Scheduled(fixedRate = 1000)
    protected void processAndSendMessages() {
        System.out.println("시작");
        groupedByStageId.forEach((stageId, queue) -> {
            executorService.submit(() -> {
                System.out.println("시작2");
                    while (!queue.isEmpty()) {
                        RequestCreateTicketDto message = queue.poll();

                        // 남은좌석개수보다 작거나 같다면 예매 로직 실행

                            // 몇 번째 순서의 요청인지 저장


                            // 처리된 메시지를 다시 Kafka로 전송
                            kafkaProducer.send(message);
                        }


            });
        });
    }
}
