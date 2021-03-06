package com.example.EmployeeHostel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.EmployeeHostel.entity.Employee;

@RestController
public class EmployeeHostelController {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	static final String EMPLOYEE_URL_MS = "http://localhost:8081/api/v1/";
	
	@GetMapping("/find/{id}")
	public String fetchStudent(@PathVariable long id) {
		Employee employee = restTemplate.exchange(EMPLOYEE_URL_MS+"employee/"+id, HttpMethod.GET,null,Employee.class).getBody();
		System.out.println("Student from Student Report Project :"+employee);
		return restTemplate.exchange(EMPLOYEE_URL_MS+"employee/"+id, HttpMethod.GET,null,String.class).getBody();
		
	}
	
	@GetMapping("/find")
	public String fetchEmployees() {
		return restTemplate.exchange(EMPLOYEE_URL_MS+"employees", HttpMethod.GET, null, String.class).getBody();
	}
	
	@PostMapping("/addEmployee")
	public String addStudent(@RequestBody Employee employee) {
		return restTemplate.postForObject(EMPLOYEE_URL_MS+"employee", employee, String.class);
	}
}


