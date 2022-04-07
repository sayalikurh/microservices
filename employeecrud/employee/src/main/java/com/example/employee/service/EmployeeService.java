package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employee.entity.Employee;
import com.example.employee.exception.ResourceNotFoundException;
import com.example.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sayalikurh
 *
 */

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Creates the Employee
	 *
	 */
	public Employee addEmployee(Employee employee) {
		log.info("Inside add employees");
		return employeeRepository.save(employee);
	}

	/**
	 * Gets the list of All Employees
	 *
	 */
	public List<Employee> getAllEmployees() {
		log.info("Inside get all employees");
		return employeeRepository.findAll();
	}

	/**
	 * Gets the Employee by id
	 *
	 */
	public Optional<Employee> getEmployeeById(long employeeId) {
		log.info("Inside get  employees by id");
		return Optional.ofNullable(employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not found  with id = " + employeeId)));

	}

	/**
	 * Deletes All Employees and returns the http status
	 *
	 */
	public ResponseEntity<HttpStatus> deleteAllEmployees() {
		log.info("Inside delete all employees");
		try {
			employeeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Deletes the employee by id
	 *
	 */
	public ResponseEntity<Employee> deleteEmployeeById(long employeeId) {
		log.info("Inside  employees by id ");
		try {
			employeeRepository.deleteById(employeeId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Updates the Employee
	 *
	 */
	public Employee updateEmployee(long employeeId, Employee newEmployee) {
		log.info("Inside update employees ");
		return employeeRepository.findById(employeeId).map(employee -> {
			employee.setFirst_name(newEmployee.getFirst_name());
			employee.setLast_name(newEmployee.getLast_name());
			employee.setAge(newEmployee.getAge());
			employee.setGender(newEmployee.getGender());
			return employeeRepository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(employeeId);
			return employeeRepository.save(newEmployee);
		});
	}

}
