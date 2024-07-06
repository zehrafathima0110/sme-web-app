package com.credmarg.smewebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.credmarg.smewebapp.entity.Employee;

public interface EmployeeService {


	Employee saveEmployee(Employee employee);
     List<Employee> getAllEmployees();
     Employee getEmployeeByMailId(String mailId);

     void deleteEmployee(String mailId);
    
}
