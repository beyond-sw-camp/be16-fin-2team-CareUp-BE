package com.careup.branch.branch.employee.repository;

import com.careup.branch.branch.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
}
