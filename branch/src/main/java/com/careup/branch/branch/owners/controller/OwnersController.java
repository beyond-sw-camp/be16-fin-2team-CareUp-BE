package com.careup.branch.branch.owners.controller;

import com.careup.branch.branch.owners.service.OwnersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/owners")
public class OwnersController {

    private final OwnersService ownersService;

//    TODO: 여기서부터 컨트롤러 코드 작성
}
