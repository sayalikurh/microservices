package com.example.EmployeeHostel.entity;

public class Employee {

	private long id;

	private String first_name;

	private String last_name;

	private int age;

	private String gender;

	public Employee() {

	}

	public Employee(long id, String first_name, String last_name, int age, String gender) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.gender = gender;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", age=" + age
				+ ", gender=" + gender + "]";
	}

}
