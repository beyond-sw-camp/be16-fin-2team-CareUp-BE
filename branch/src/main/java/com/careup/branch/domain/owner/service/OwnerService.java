<<<<<<<< HEAD:branch/src/main/java/com/careup/branch/domain/owner/service/OwnerService.java
package com.careup.branch.domain.owner.service;

import com.careup.branch.domain.owner.repository.OwnerRepository;
========
package com.careup.branch.branch.owner.service;

import com.careup.branch.branch.owner.repository.OwnerRepository;
>>>>>>>> 5658d4c803a6945f55b61cb6d4d6c53854c8927e:branch/src/main/java/com/careup/branch/branch/owner/service/OwnerService.java
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
