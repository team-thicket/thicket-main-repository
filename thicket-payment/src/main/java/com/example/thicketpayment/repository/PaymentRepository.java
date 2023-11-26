package com.example.thicketpayment.repository;

import com.example.thicketpayment.domain.Payment;
import com.example.thicketpayment.dto.response.ResponsePaymentForMemberDto;
import com.example.thicketpayment.dto.response.ResponsePaymentForStageDto;
import com.example.thicketpayment.enumerate.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByUuid(String paymentId);
    @Modifying // jpa를 통한 업데이트 쿼리는 영속성 컨테이너 초기화가 필수임. 그걸 도와주는 어노테이션 Modifying
    @Query("UPDATE Payment p " +
            "SET p.cancelDeadline = :newCancelDate " +
            "WHERE p.stageUuid = :stageId " +
            "AND p.state = :state")
    int updateCancelDateByStageUuid(@Param("stageId") String stageUuid,
                                    @Param("newCancelDate") LocalDateTime newCancelDate,
                                    @Param("state") State state);

    @Query("SELECT new com.example.thicketpayment.dto.response.ResponsePaymentForMemberDto(p) " +
            "FROM Payment p " +
            "WHERE p.memberUuid = :memberId")
    List<ResponsePaymentForMemberDto> findAllByMemberId(@Param("memberId") String memberId);

    @Query("SELECT new com.example.thicketpayment.dto.response.ResponsePaymentForStageDto(p) " +
            "FROM Payment p " +
            "WHERE p.stageUuid = :stageId")
    List<ResponsePaymentForStageDto> findAllByStageId(@Param("stageId") String stageId);
}
