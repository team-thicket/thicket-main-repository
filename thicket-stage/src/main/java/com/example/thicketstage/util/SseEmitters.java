package com.example.thicketstage.util;

import com.example.thicketstage.exception.ServerConnectionOutException;
import com.example.thicketstage.service.StageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
@Component
@RequiredArgsConstructor
@Slf4j
public class SseEmitters {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final StageService stageService;

    public SseEmitter add(String memberId, String stageId, SseEmitter emt) {
        // 첫 연결시 응답 스트림을 구성하지않으면 타임아웃이 발생하는 현상을 방지하기위해
        // 일단 connected!라는 대답을 리턴
        try {
            emt.send(SseEmitter.event()
                    .id(memberId)
                    .name("connect")
                    .data("connected!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String key = memberId + " " + stageId;
        emitters.put(key, emt);

        // SSE가 완료처리 되면 Map에서 삭제
        emt.onCompletion(() -> this.emitters.remove(key));

        // SSE가 만료되면 완료로 처리함
        emt.onTimeout(emt::complete);
        return emt;
    }

    @Scheduled(fixedDelay = 1000)
    public void check() {
        if (emitters.isEmpty()) {
            return;
        }
        // sse 연결객체 목록을 순회
        Set<String> keys = emitters.keySet();
        for (String k : keys) {
            String[] key = k.split(" ");
            //key를 split함
            // 0 index -> memberId
            // 1 index -> stageId
            String stageStatus = stageService.checkOpenDate(UUID.fromString(key[1]));
            log.info(stageStatus);
            SseEmitter sseEmitter = emitters.get(k);
            try {
                sseEmitter.send(SseEmitter.event()
                        .id(key[0])
                        .name("isOngoing")
                        .data(stageStatus));
            } catch (IOException e) {
                throw new ServerConnectionOutException("sse오류");
            } finally {
                if (stageStatus.equals("Yes")) {
                    sseEmitter.complete();
                }
            }
        }
    }
}
