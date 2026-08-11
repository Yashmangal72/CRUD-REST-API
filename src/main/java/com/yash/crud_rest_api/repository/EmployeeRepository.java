package com.yash.crud_rest_api.repository;


import com.yash.crud_rest_api.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {

    private final Map<Integer, Employee> employees = new HashMap<>();

    private int idCounter = 1;

    public Employee save(Employee employee) {
        if (employee.getId() == 0) {
            employee.setId(idCounter++);
        }
        employees.put(employee.getId(), employee);
        return employee;
    }

    public List<Employee> findAll(){
        return new ArrayList<>(employees.values());
    }

    public Employee findById (int id){
        return employees.get(id);
    }

    public boolean existsById (int id){
        return employees.containsKey(id);
    }

    public void deleteById (int id) {
        employees.remove(id);
    }
 }
