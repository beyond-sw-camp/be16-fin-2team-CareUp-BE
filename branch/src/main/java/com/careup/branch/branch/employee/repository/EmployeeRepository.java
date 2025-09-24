package com.careup.branch.branch.employee.repository;

import com.careup.branch.branch.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
