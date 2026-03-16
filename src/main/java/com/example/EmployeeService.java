package com.example;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class EmployeeService {
 
	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public Employee register(Employee employee) {
		employee.setPassword(encoder.encode(employee.getPassword()));
		return repo.save(employee);
	}
	
	public List<Employee> getAll() {
		return repo.findAll();
	}
	
	public Employee update(Long id, Employee emp) {
		Employee existing = getById(id);
		existing.setName(emp.getName());
		existing.setDepartment(emp.getDepartment());
		existing.setSalary(emp.getSalary());
		return repo.save(existing);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Employee getById(Long id) {
		return  repo.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found!"));
	}
}
