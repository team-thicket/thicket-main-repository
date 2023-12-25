//package com.example.thicketbatch.job;
//
//import com.example.thicketbatch.dto.request.RequestCreateTicketDto;
//import com.example.thicketbatch.service.KafkaProducer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.PriorityQueue;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Component
//@RequiredArgsConstructor
//public class BatchJobByTime {
//
//    private final KafkaProducer kafkaProducer;
//    private final ObjectMapper objectMapper;
//
//    // ConcurrentHashMap으로 변경9
//    private final Map<String, PriorityQueue<RequestCreateTicketDto>> groupedByStageId = new ConcurrentHashMap<>();
//
//    // ExecutorService를 사용하여 각 stageId별로 비동기 작업을 처리
//    private final Map<String, ExecutorService> executorServices = new ConcurrentHashMap<>();
//
//    @KafkaListener(topics = "test", groupId = "group-id-1")
//    public void consume(ConsumerRecord<String, String> record) {
//        try {
//            RequestCreateTicketDto requestCreateTicketDto = objectMapper.readValue(record.value(), RequestCreateTicketDto.class);
//            System.out.println("Received message: " + requestCreateTicketDto.toString());
//            addToQueue(requestCreateTicketDto);
//        } catch (Exception e) {
//            System.err.println("Error processing Kafka message: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public void addToQueue(RequestCreateTicketDto message) {
//        String stageId = message.getStageId();
//        groupedByStageId.computeIfAbsent(stageId, k -> new PriorityQueue<>()).offer(message);
//        System.out.println("Added message to queue. StageId: " + stageId + ", Message: " + message);
//
//        // 해당 stageId에 대한 ExecutorService 생성 (최초 한 번만 생성)
//        executorServices.computeIfAbsent(stageId, k -> Executors.newCachedThreadPool());
//
//        // 해당 stageId에 대한 작업을 ExecutorService에 submit
//        executorServices.get(stageId).submit(() -> processAndSendMessages(stageId));
//    }
//
//    private void processAndSendMessages(String stageId) {
//        // 해당 stageId에 대한 우선순위 큐를 처리하고 결과를 전송
//        groupedByStageId.computeIfPresent(stageId, (k, queue) -> {
//            while (!queue.isEmpty()) {
//                RequestCreateTicketDto message = queue.poll();
//                // 여기에서 원하는 처리 수행
//
//                // 처리된 메시지를 다시 Kafka로 전송
//                kafkaProducer.send(message);
//            }
//            return queue;
//        });
//    }
//
//
//    @Scheduled(fixedRate = 1000)
//    protected void scheduledProcessing() {
//        // 모든 stageId에 대해 작업이 완료될 때까지 대기
//        CountDownLatch latch = new CountDownLatch(groupedByStageId.size());
//
//        // 각 stageId에 대한 작업을 병렬로 처리
//        for (String stageId : groupedByStageId.keySet()) {
//            executorServices.get(stageId).submit(() -> {
//                processAndSendMessages(stageId);
//                latch.countDown();
//            });
//        }
//
//        // 모든 stageId에 대한 작업이 완료될 때까지 대기
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            System.err.println("Error waiting for tasks to complete: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
