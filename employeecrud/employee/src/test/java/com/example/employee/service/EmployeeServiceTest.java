package com.example.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class EmployeeServiceTest {
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
		
	@Test
	void testGetAllEmployees() {
		List<Employee> employees=new ArrayList<>();
		Employee emp=new Employee();
		emp.setFirst_name("sayali");
		emp.setLast_name("kurhade");
		emp.setAge(27);
		emp.setGender("female");
		employees.add(emp);
		Mockito.when(this.employeeRepository.findAll()).thenReturn(employees);
		final var actual=this.employeeService.getAllEmployees();
		assertEquals("sayali", actual.get(0).getFirst_name());
	}
	
	@Test
	void testAddEmployee() {
		Employee emp=new Employee();
		emp.setFirst_name("sayali");
		emp.setLast_name("kurhade");
		emp.setAge(27);
		emp.setGender("female");
		Mockito.when(this.employeeRepository.save(emp)).thenReturn(emp);
		final var actual=this.employeeService.addEmployee(emp);
		assertEquals("sayali", actual.getFirst_name());
	}
	
	@Test
	void testGetEmployeeById() {
		Employee emp=new Employee();
		emp.setId(1);
		emp.setFirst_name("sayali");
		emp.setLast_name("kurhade");
		emp.setAge(27);
		emp.setGender("female");
		Mockito.when(this.employeeRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
		final var actual=this.employeeService.getEmployeeById(1);
		assertEquals(27, actual.get().getAge());
	}
	
	@Test
	void testDeleteAllEmployees() {
		Mockito.doNothing().when(this.employeeRepository)
		.deleteAll();
		this.employeeService.deleteAllEmployees();
		verify(employeeRepository, times(1)).deleteAll();
	}

	@Test
	void testDeleteEmployeeById() {
		Employee emp=new Employee();
		emp.setId(1);
		Mockito.doNothing().when(this.employeeRepository)
		.deleteById(emp.getId());
		this.employeeService.deleteEmployeeById(emp.getId());
		verify(employeeRepository, times(1)).deleteById(emp.getId());
	}
	
	@Test
	void testUpdateEmployee() {
		Employee emp=new Employee();
		emp.setId(10);
		emp.setId(1);
		emp.setFirst_name("sayali");
		emp.setLast_name("kurhade");
		emp.setAge(27);
		emp.setGender("female");
		Employee newEmp=new Employee();
		newEmp.setId(1);
		newEmp.setFirst_name("chaitali");
		newEmp.setLast_name("kurhade");
		newEmp.setAge(27);
		newEmp.setGender("female");
		Mockito.when(this.employeeRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
		Mockito.when(this.employeeRepository.save(emp)).thenReturn(newEmp);
		final var actual=this.employeeService.updateEmployee(emp.getId(), newEmp);
		assertNotNull(actual);
	}

}
