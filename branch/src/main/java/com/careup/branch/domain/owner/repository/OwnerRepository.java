package com.careup.branch.domain.owner.repository;

import com.careup.branch.domain.owner.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    // TODO: 여기서부터 리포지토리 코드 작성
}
