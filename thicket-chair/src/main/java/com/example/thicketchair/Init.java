package com.example.thicketchair;


import com.example.thicketchair.domain.Chair;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Init {
    private final InitService initService;


    @PostConstruct
    public void init(){
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void init() {
            Chair chair = Chair.createChair(
                    "VIP",
                    100,
                    189_000
                    );
            em.persist(chair);
        }
    }
}
