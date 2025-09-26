package com.careup.ordering.ordering.member.repository;

import com.careup.ordering.ordering.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
