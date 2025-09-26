<<<<<<<< HEAD:branch/src/main/java/com/careup/branch/domain/owner/controller/OwnerController.java
package com.careup.branch.domain.owner.controller;

import com.careup.branch.domain.owner.service.OwnerService;
========
package com.careup.branch.branch.owner.controller;

import com.careup.branch.branch.owner.service.OwnerService;
>>>>>>>> 5658d4c803a6945f55b61cb6d4d6c53854c8927e:branch/src/main/java/com/careup/branch/branch/owner/controller/OwnerController.java
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

//    TODO: 여기서부터 컨트롤러 코드 작성
}
