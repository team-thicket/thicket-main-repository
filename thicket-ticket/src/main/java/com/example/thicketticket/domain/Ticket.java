    package com.example.thicketticket.domain;

    import com.example.thicketticket.TimeStamp;
    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.SQLDelete;

    @Entity
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class Ticket extends TimeStamp {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String stageName;

        @Column(nullable = false)
        private String date;

        @Column(nullable = false)
        private String place;

        @Column(nullable = false)
        private String chairType;

        @Column(nullable = false)
        private String memberName;

        @Column(nullable = false)
        private int price;

        @Column(nullable = false)
        private String ticketNumber;

        @Column(nullable = false)
        private Long stageId;

        @Column(nullable = false)
        private Long memberId;

        @Column(nullable = false)
        private boolean deleted = false;

        // 테스트용 메서드
        public static Ticket createTicket(String stageName,
                                          String date,
                                          String place,
                                          String chairType,
                                          String memberName,
                                          int price,
                                          String ticketNumber,
                                          long stageId,
                                          long memberId) {
            Ticket ticket = new Ticket();
            ticket.stageName = "Test Stage";
            ticket.date = "2023-11-30";
            ticket.place = "Test Place";
            ticket.chairType = "VIP";
            ticket.memberName = "Test Member";
            ticket.price = 50000;
            ticket.ticketNumber = "T123456";
            ticket.stageId = 100L;
            ticket.memberId = 200L;

            return ticket;
        }
        public void updateDeleted(boolean deleted) {
            this.deleted = deleted;
        }

    }
