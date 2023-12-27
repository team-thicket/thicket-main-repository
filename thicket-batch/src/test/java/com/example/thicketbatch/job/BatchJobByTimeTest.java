//package com.example.thicketbatch.job;
//
//import com.example.thicketbatch.dto.request.RequestCreateTicketDto;
//import com.example.thicketbatch.service.KafkaProducer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class BatchJobByTimeTest {
//
//    @InjectMocks
//    private BatchJobByTime batchJob;
//
//    @Mock
//    private KafkaProducer kafkaProducer;
//
//    @Mock
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void testConsume() throws Exception {
//        // Arrange
//        String stageId = "1";
//        String messageJson = "{\"stageId\":\"" + stageId + "\",\"otherField\":\"value\"}";
//        ConsumerRecord<String, String> record = new ConsumerRecord<>("test", 1, 1, "key", messageJson);
//        RequestCreateTicketDto expectedDto = new RequestCreateTicketDto();
//        expectedDto.setStageId(stageId);
//
//        when(objectMapper.readValue(eq(messageJson), eq(RequestCreateTicketDto.class))).thenReturn(expectedDto);
//
//        // Act
//        batchJob.consume(record);
//
//        // Assert
//        verify(kafkaProducer, times(1)).send(eq(expectedDto));
//    }
//
//    @Test
//    public void testScheduledProcessing() {
//        // Arrange
//        String stageId = "1";
//        RequestCreateTicketDto ticketDto = new RequestCreateTicketDto();
//        ticketDto.setStageId(stageId);
//
//        batchJob.addToQueue(ticketDto);
//
//        // Act
//        batchJob.scheduledProcessing();
//
//        // Assert
//        verify(kafkaProducer, times(1)).send(eq(ticketDto));
//    }
//}
