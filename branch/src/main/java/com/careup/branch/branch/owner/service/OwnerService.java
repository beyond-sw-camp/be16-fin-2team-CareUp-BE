package com.careup.branch.branch.owner.service;

import com.careup.branch.branch.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerService {

    private final OwnerRepository ownerRepository;

//    TODO: 여기서부터 서비스 코드 작성
}
