    package com.example.thicketticket.domain;

    import com.example.thicketticket.TimeStamp;
    import com.example.thicketticket.enumerate.Status;
    import jakarta.persistence.*;
    import lombok.*;

    import java.time.LocalDateTime;
    import java.util.UUID;

    @Entity
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class Ticket extends TimeStamp {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(nullable = false)
        private String stageName;

        @Column(nullable = false)
        private String stageType;

        @Column(nullable = false)
        private LocalDateTime date;

        @Column(nullable = false)
        private String place;

        @Column(nullable = false)
        private String chairType;

        @Column(nullable = false)
        private int count;

        @Column(nullable = false)
        private int price;

        @Column(nullable = false)
        private String memberName;

        @Column
        private String phone;

        @Column(nullable = false)
        private LocalDateTime cancelDate;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Status status;

        @Column
        private int sequence;
        @Column(nullable = false)
        private String stageId;

        @Column(nullable = false)
        private String memberId;

        @Column(nullable = false)
        private boolean deleted;

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        private Payment payment;

        public static Ticket createTicket(String newStageName, String newPlace, LocalDateTime newDate,
                                          String newChairType, int newCount, String newMemberName,
                                          String newPhone, int newPrice, LocalDateTime newCancelDate,
                                          String newStageId, String newMemberId, String newStageType
                                          ) {
            Ticket ticket = new Ticket();


            ticket.deleted = false;
            ticket.stageName = newStageName;
            ticket.date = newDate;
            ticket.place = newPlace;
            ticket.chairType = newChairType;
            ticket.count = newCount;
            ticket.memberName = newMemberName;
            ticket.price = newPrice;
            ticket.phone = newPhone;
            ticket.cancelDate = newCancelDate;
            ticket.stageId = newStageId;
            ticket.memberId = newMemberId;
            ticket.stageType = newStageType;
            ticket.status = Status.RESERVE;

            return ticket;
        }
        public void updateDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public void setPayment(Payment payment) {
            this.payment = payment;
        }
    }
