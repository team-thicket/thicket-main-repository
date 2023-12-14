    package com.example.thicketticket.domain;

    import com.example.thicketticket.TimeStamp;
    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.SQLDelete;

    import java.time.LocalDateTime;

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
        private LocalDateTime date;

        @Column(nullable = false)
        private String place;

        @Column(nullable = false)
        private String chairType;

        @Column(nullable = false)
        private int count;

        @Column(nullable = false)
        private String memberName;

        @Column(nullable = false)
        private int price;

        @Column
        private int sequence;

        @Column(nullable = false)
        private LocalDateTime cancelDate;

        @Column
        private String howReceive;

        @Column
        private String method;

        @Column
        private String status;

        @Column(nullable = false)
        private Long stageId;

        @Column(nullable = false)
        private Long memberId;

        @Column(nullable = false)
        private boolean deleted;

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        private Payment payment;

        // 테스트용 메서드
        public static Ticket createTicket(String newStageName, LocalDateTime newDate, String newPlace,
                                          String newChairType, int newCount, String newMemberName,
                                          int newPrice, int newSequence, LocalDateTime newCancelDate,
                                           long newStageId, long newMemberId) {
            Ticket ticket = new Ticket();

            ticket.deleted = false;
            ticket.stageName = newStageName;
            ticket.date = newDate;
            ticket.place = newPlace;
            ticket.chairType = newChairType;
            ticket.count = newCount;
            ticket.memberName = newMemberName;
            ticket.price = newPrice;
            ticket.sequence = newSequence;
            ticket.cancelDate = newCancelDate;
            ticket.stageId = newStageId;
            ticket.memberId = newMemberId;

            return ticket;
        }
        public void updateDeleted(boolean deleted) {
            this.deleted = deleted;
        }

    }
