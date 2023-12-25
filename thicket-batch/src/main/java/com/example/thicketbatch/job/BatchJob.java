package com.example.thicketbatch.job;

import com.example.thicketbatch.dto.request.RequestCreateTicketDto;
import com.example.thicketbatch.repository.ChairRepository;
import com.example.thicketbatch.service.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class BatchJob {

    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;
    private final ChairRepository chairRepository;
    // chairId별로 메시지를 쌓을 Map
    private Map<String, PriorityQueue<RequestCreateTicketDto>> groupedByStageId = new ConcurrentHashMap<>();

    // 각 chairId에 대한 mCount를 유지하는 Map
    private Map<String, AtomicInteger> mCountMap = new ConcurrentHashMap<>();

    @KafkaListener(topics = "test", groupId = "group-id-1")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            // Kafka 메시지의 값을 RequestCreateTicketDto로 역직렬화
            RequestCreateTicketDto requestCreateTicketDto = objectMapper.readValue(record.value(), RequestCreateTicketDto.class);
            // consume한 메세지
            System.out.println("Received message: " + requestCreateTicketDto.toString());
            // chairId를 기준으로 메시지를 큐에 추가
            addToQueue(requestCreateTicketDto);
        } catch (Exception e) {
            // 역직렬화나 처리 중에 오류가 발생한 경우 예외 처리
            System.err.println("Error processing Kafka message: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //큐에 메세지 추가
    private void addToQueue(RequestCreateTicketDto message) {
        String stageId = message.getStageId();
        groupedByStageId
                .computeIfAbsent(stageId, k -> new PriorityQueue<>())
                .offer(message);
        System.out.println("Added message to queue. StageId: " + stageId + ", Message: " + message);
    }

    //메세지 전송 및 업데이트
    @Scheduled(fixedRate = 1000)
    @Async
    @Transactional
    protected void processAndSendMessages() {
        groupedByStageId.forEach((stageId, queue) -> {
            log.info(stageId);
            //chairId별 rank생성
            AtomicInteger rank = mCountMap.computeIfAbsent(stageId, k -> new AtomicInteger(0));

            //남은좌석조회,db의 availablecount 가져옴
            Integer availableCount = chairRepository.findCountByChairId(UUID.fromString(stageId));
            log.info(String.valueOf(availableCount));

            //queue.length의 80% 만 예매진행
            int ql = 0;
            if (queue.size() > 1) {
                ql = (int) (queue.size() * 0.8);
            } else if (queue.size() == 1) {
                ql = 1;
            }

            //예매 진행 이후 잔여 좌석수
            int updateCount = availableCount - ql;
            log.info(String.valueOf(updateCount));
            //큐의 메세지 전부 유효한 메세지 일 때
            if (updateCount >= 0) {
                //좌석 데이터 업데이트
                chairRepository.updateCountByChairId(UUID.fromString(stageId), updateCount);
                chairRepository.flush();
                //ordered 토픽으로 메세지 전송
                producer(rank,queue,ql,updateCount);
            }
            // 일부만 유효한 메세지 일 때
            else {
                //남은좌석 수 만큼만 예매진행
                //남은좌석 데이터 업데이트
                chairRepository.updateCountByChairId(UUID.fromString(stageId), 0);
                chairRepository.flush();

                //ordered 토픽으로 메세지 전송
                producer(rank,queue,availableCount,updateCount);
                //이후 프론트에서 버튼 비활성화
                //else 유효하지 않은 예매 요청
                //순서 -1
                //카프카 토픽전송s
                //
            }
        });
    }

    public void producer(AtomicInteger rank, Queue<RequestCreateTicketDto> queue,int Count,int updateCount) {
        while (Count!=0) {

            int currentCount = rank.incrementAndGet();
            log.info("id별rank:" + currentCount);
            //메세지 추출
            RequestCreateTicketDto message = queue.poll();
            //메세지 sequence 입력
            message.setSequence(currentCount);
            log.info(String.valueOf(message.getSequence()));

            // 처리된 메시지를 다시 Kafka로 전송
            kafkaProducer.send(message);
            Count--;
        }
        if(updateCount<0) {
            while (!queue.isEmpty()) {
                //메세지 순서 입력
                int currentCount = -1;
                log.info("id별rank:" + currentCount);
                //메세지 추출
                RequestCreateTicketDto message = queue.poll();
                message.setSequence(currentCount);
                log.info(String.valueOf(message.getSequence()));

                // 처리된 메시지를 다시 Kafka로 전송
                kafkaProducer.send(message);

            }
        }
    }
}
