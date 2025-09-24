package com.careup.branch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/health")
    public String healthCheck(){
        System.out.println("healthcheck중입니다");
        return "Branch-Service OK";
    }
}
