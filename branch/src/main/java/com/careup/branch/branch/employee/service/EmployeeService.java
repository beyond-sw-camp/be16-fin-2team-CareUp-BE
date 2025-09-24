package com.careup.branch.branch.employee.service;

import com.careup.branch.branch.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // TODO: 직원 관련 비즈니스 로직 추가
}
