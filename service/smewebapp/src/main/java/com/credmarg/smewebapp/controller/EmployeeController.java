package com.credmarg.smewebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credmarg.smewebapp.entity.Employee;
import com.credmarg.smewebapp.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/sme/employee")
public class EmployeeController {

	@Autowired
	private  EmployeeServiceImpl employeeService;


	 @PostMapping("/createEmployee")
	 public Employee createEmployee(@RequestBody Employee employee) {
	     return employeeService.saveEmployee(employee);
	 }
	
	 @GetMapping("/allEmployees")
	 public List<Employee> getAllEmployees() {
	     return employeeService.getAllEmployees();
	 }
	 
	 @PutMapping("/updateEmployee/{mailId}")
	    public Employee updateEmployee(@PathVariable String mailId, @RequestBody Employee updatedEmployee) {
		 try {
	        Employee employee = employeeService.getEmployeeByMailId(mailId);
	        if (employee != null) {
	            employee.setName(updatedEmployee.getName());
	            employee.setEmail(updatedEmployee.getEmail());
	            employee.setDesignation(updatedEmployee.getDesignation());
	            employee.setCtc(updatedEmployee.getCtc());
	            return employeeService.saveEmployee(employee);
	        }
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }
	        return null; 
	    }

	 	@DeleteMapping("/deleteEmployee/{mailId}")
	    public void deleteEmployee(@PathVariable String mailId) {
	        employeeService.deleteEmployee(mailId);
	    }
	
}
