package com.careup.branch.domain.owner.controller;

import com.careup.branch.domain.owner.service.OwnerService;
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
