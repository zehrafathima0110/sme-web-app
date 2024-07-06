package com.credmarg.smewebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credmarg.smewebapp.entity.Employee;
import com.credmarg.smewebapp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		employee.setEmail(employee.getEmail().toLowerCase());
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeByMailId(String mailId) {
		Employee employee = null;
		try {
			employee = employeeRepository.findById(mailId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public void deleteEmployee(String mailId) {
		try {
			employeeRepository.deleteById(mailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
