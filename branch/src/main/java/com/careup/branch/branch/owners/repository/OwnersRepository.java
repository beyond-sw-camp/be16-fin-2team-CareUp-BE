package com.careup.branch.branch.owners.repository;

import com.careup.branch.branch.owners.entity.Owners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnersRepository extends JpaRepository<Owners, Long> {
    Owners findByEmail(String email);
    // TODO: 여기서부터 리포지토리 코드 작성
}
