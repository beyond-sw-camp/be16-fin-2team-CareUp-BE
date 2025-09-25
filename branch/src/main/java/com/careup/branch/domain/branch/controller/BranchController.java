package com.careup.branch.domain.branch.controller;

import com.careup.branch.domain.branch.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    // TODO: 지점 관련 API 추가
}
