package com.yash.crud_rest_api.controller;


import com.yash.crud_rest_api.dto.EmployeeRequest;
import com.yash.crud_rest_api.dto.EmployeeResponse;
import com.yash.crud_rest_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody EmployeeRequest request
            ){
        EmployeeResponse response = service.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){

        List<EmployeeResponse> employees = service.getAllEmployees();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(
            @PathVariable int id){

        EmployeeResponse response = service.getEmployee(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable int id,
            @Valid @RequestBody EmployeeRequest request){

        EmployeeResponse response = service.updateEmployee(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable int id
    ) {
        service.deleteEmployee(id);
        return  ResponseEntity.noContent().build();
    }

}
