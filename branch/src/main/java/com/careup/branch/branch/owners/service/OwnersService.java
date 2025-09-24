package com.careup.branch.branch.owners.service;

import com.careup.branch.branch.owners.repository.OwnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnersService {

    private final OwnersRepository ownersRepository;

//    TODO: 여기서부터 서비스 코드 작성
}
