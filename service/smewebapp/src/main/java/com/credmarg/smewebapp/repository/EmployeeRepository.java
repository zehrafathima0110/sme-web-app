package com.credmarg.smewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.credmarg.smewebapp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,String>{

}
