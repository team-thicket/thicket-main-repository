package com.example.thicketmember.repository;

import com.example.thicketmember.domain.Member;
import com.example.thicketmember.enumerate.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByEmail(String email);
    Member findMemberByEmail(String email);
    Member findMemberById(UUID id);

    List<Member> findAllByMemberRole(MemberRole role);
}
