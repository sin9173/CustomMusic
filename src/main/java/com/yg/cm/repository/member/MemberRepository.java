package com.yg.cm.repository.member;

import com.yg.cm.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String user_id);

    @Query("select m from Member m left join fetch m.authorities")
    Optional<Member> findAuthByUserId(String user_id);
}
