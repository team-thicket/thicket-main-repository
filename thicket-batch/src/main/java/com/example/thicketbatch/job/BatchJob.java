package com.example.thicketbatch.job;

import com.example.thicketbatch.dto.request.RequestCreateTicketDto;
import com.example.thicketbatch.repository.ChairRepository;
import com.example.thicketbatch.repository.StageRepository;
import com.example.thicketbatch.service.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@Slf4j
public class BatchJob {

    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;
    private final ChairRepository chairRepository;
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
            int currentCount = chairRepository.findAvailableCountByUuid(requestCreateTicketDto.getStageId());
            System.out.println(currentCount);
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

        groupedByStageId.forEach((stageId, queue) -> {
            executorService.submit(() -> {
                //남은좌석조회
                log.debug("Before query execution. StageId: {}", stageId);
                int currentCount = chairRepository.findAvailableCountByUuid(stageId);
                log.debug("After query execution. StageId: {}, Count: {}", stageId, currentCount);

                //현재 대기개수
//                int count =groupedByStageId.length;


                    while (!queue.isEmpty()) {
                        RequestCreateTicketDto message = queue.poll();
                        log.info(message+"@@@@@@@@@@@");
//                        currentCount++;

                            // 몇 번째 순서의 요청인지 저장

                            //오더링 결과가 유효한지 TEST
                            // 처리된 메시지를 다시 Kafka로 전송
                            kafkaProducer.send(message);
                        }
                //남은 좌석 개수에서 count차감

            });
        });
    }
}
