package com.careup.branch.domain.employee.repository;

import com.careup.branch.domain.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
