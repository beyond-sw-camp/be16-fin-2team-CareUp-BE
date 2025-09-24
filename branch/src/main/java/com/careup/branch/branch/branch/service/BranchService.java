package com.careup.branch.branch.branch.service;

import com.careup.branch.branch.branch.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BranchService {

    private final BranchRepository branchRepository;

    // TODO: 지점 관련 비즈니스 로직 추가
}
