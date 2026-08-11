package com.yash.crud_rest_api.repository;

import com.yash.crud_rest_api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}