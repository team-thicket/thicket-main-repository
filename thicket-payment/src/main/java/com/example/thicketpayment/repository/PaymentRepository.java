package com.example.thicketpayment.repository;

import com.example.thicketpayment.domain.Payment;
import com.example.thicketpayment.dto.response.ResponseStateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findAllByStageUuid(String stageUuid);

    List<Payment> findAllByMemberUuid(String memberUuid);
    @Modifying // jpa를 통한 업데이트 쿼리는 영속성 컨테이너 초기화가 필수임. 그걸 도와주는 어노테이션 Modifying
    @Query("UPDATE Payment p " +
            "SET p.cancelDate = :newCancelDate " +
            "WHERE p.stageUuid = :stageId")
    int updateCancelDateByStageUuid(@Param("stageId") String stageUuid,
                                     @Param("newCancelDate") LocalDateTime newCancelDate);

    // 조회 쿼리 최적화 -> DTO로 조회 예시
//    @Query("SELECT new com.example.thicketpayment.dto.response.ResponseStateDto(p) " +
//            "FROM Payment p " +
//            "WHERE p.memberUuid = :memberId")
//    List<ResponseStateDto> findStatesByMemberId(@Param("memberId") String memberId);
}
