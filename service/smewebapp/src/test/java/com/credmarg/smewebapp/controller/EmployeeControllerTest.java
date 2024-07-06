package com.credmarg.smewebapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.credmarg.smewebapp.entity.Employee;
import com.credmarg.smewebapp.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setEmail("john@example.com");
        employee.setDesignation("Developer");
        employee.setCtc(60000);

        when(employeeService.saveEmployee(employee)).thenReturn(employee);

        //System.out.println("******"+objectMapper.writeValueAsString(employee));  
        mockMvc.perform(post("/sme/employee/createEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$[0].name").value("John Doe"))
                //.andExpect(jsonPath("$.email").value("john@example.com"));
        
        }

    @Test
    public void testGetAllEmployees() throws Exception {
    	Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setEmail("john@example.com");
        employee.setDesignation("Developer");
        employee.setCtc(60000);
        List<Employee> employees = Arrays.asList(employee);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/sme/employee/allEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john@example.com"));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
    	Employee existingEmployee = new Employee();
    	existingEmployee.setName("John Doe");
    	existingEmployee.setEmail("john@example.com");
    	existingEmployee.setDesignation("Developer");
    	existingEmployee.setCtc(60000);
        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("John Doe");
        updatedEmployee.setEmail("john@example.com");
        updatedEmployee.setDesignation("Developer");
        updatedEmployee.setCtc(70000);
        
        when(employeeService.getEmployeeByMailId("john@example.com")).thenReturn(existingEmployee);
        when(employeeService.saveEmployee(existingEmployee)).thenReturn(updatedEmployee);

        mockMvc.perform(put("/sme/employee/updateEmployee/john@example.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ctc").value(70000));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/sme/employee/deleteEmployee/john@example.com"))
                .andExpect(status().isOk());
    }
}
