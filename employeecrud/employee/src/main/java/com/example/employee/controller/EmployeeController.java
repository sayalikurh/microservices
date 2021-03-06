package com.example.employee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	@ResponseBody
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<>(this.employeeService.addEmployee(employee), HttpStatus.CREATED);
	}

	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployees() {
		return new ResponseEntity<>(this.employeeService.getAllEmployees(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
		Optional<Employee> employeeData = employeeService.getEmployeeById(employeeId);
		if (employeeData.isPresent()) {
			return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/employees")
	public ResponseEntity<HttpStatus> deleteAllEmployees() {
		return employeeService.deleteAllEmployees();
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") long employeeId) {
		return employeeService.deleteEmployeeById(employeeId);

	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,
			@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(this.employeeService.updateEmployee(employeeId, employee),
				HttpStatus.ACCEPTED);
	}
}