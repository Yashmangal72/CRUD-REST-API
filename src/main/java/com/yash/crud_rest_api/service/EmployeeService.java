package com.yash.crud_rest_api.service;


import com.yash.crud_rest_api.dto.EmployeeRequest;
import com.yash.crud_rest_api.dto.EmployeeResponse;
import com.yash.crud_rest_api.exception.EmployeeNotFoundException;
import com.yash.crud_rest_api.model.Employee;
import com.yash.crud_rest_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService (EmployeeRepository repository){
        this.repository = repository;
    }

    public EmployeeResponse createEmployee (EmployeeRequest request){
        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());

        Employee savedEmployee = repository.save(employee);

        return new EmployeeResponse(
                savedEmployee.getId(),
                savedEmployee.getName(),
                savedEmployee.getDepartment(),
                savedEmployee.getEmail(),
                savedEmployee.getSalary()
        );
    }

    public List<EmployeeResponse> getAllEmployees (){
        return repository.findAll()
                .stream()
                .map(employee -> new EmployeeResponse(
                        employee.getId(),
                        employee.getName(),
                        employee.getDepartment(),
                        employee.getEmail(),
                        employee.getSalary()
                ))
                .toList();
    }

    public EmployeeResponse getEmployee(int id) {
        Employee employee = repository.findById(id);

        if (employee == null) {
            throw new EmployeeNotFoundException (
                    "Employee with ID : " + id + " not found");
        }
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getEmail(),
                employee.getSalary()
        );
    }

    public EmployeeResponse updateEmployee (int id, EmployeeRequest request){
        Employee existingEmployee = repository.findById(id);

        if (existingEmployee == null) {
            throw new EmployeeNotFoundException(
                    "Employee with ID : " + id + " not found"
            );
        }
        existingEmployee.setName(request.getName());
        existingEmployee.setDepartment(request.getDepartment());
        existingEmployee.setEmail(request.getEmail());
        existingEmployee.setSalary(request.getSalary());

        Employee updatedEmployee = repository.save(existingEmployee);

        return new EmployeeResponse(
                updatedEmployee.getId(),
                updatedEmployee.getName(),
                updatedEmployee.getDepartment(),
                updatedEmployee.getEmail(),
                updatedEmployee.getSalary()
        );
    }

    public void deleteEmployee (int id) {
        if(!repository.existsById(id)){
            throw new EmployeeNotFoundException(
                    "Employee with ID : " + id + " not found"
            );
        }
        repository.deleteById(id);
    }
}
