package com.example;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="empldb")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private String department;
	private Double salary;
	private String role;
	
	
	public Employee(Long id, String name, String email, String password, String department, Double salary,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.department = department;
		this.salary = salary;
		this.role = role;
	}
	
	
	public Employee(String name, String email, String password, String department, Double salary, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.department = department;
		this.salary = salary;
		this.role = role;
	}

	public Employee() {
	
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
