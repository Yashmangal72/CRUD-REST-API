package com.yash.crud_rest_api.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException (String message){
        super(message);
    }
}
