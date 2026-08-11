package com.yash.crud_rest_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {

    @NotBlank(message = "Name is required")
    @Size(
            min = 2,
            max = 50,
            message = "Name must be between 2 and 50 characters"
    )
    private String name;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @Positive(message = "Salary must be greater than 0")
    private double salary;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String name, String department, String email, double salary) {
        this.name = name;
        this.department = department;
        this.email = email;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
