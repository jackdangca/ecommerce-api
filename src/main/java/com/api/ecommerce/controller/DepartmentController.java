package com.api.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ecommerce.exception.ResourceNotFoundException;
import com.api.ecommerce.model.Department;
import com.api.ecommerce.repository.DepartmentRepository;

@RestController
@RequestMapping("")
public class DepartmentController {

	@Autowired
	DepartmentRepository departmentRepository;

	/**
	 * Returns all departments
	 *
	 * @return - List<Department>
	 */
	@GetMapping("/departments")
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	/**
	 * Returns department by department id
	 *
	 * @param departmentId - Department Id
	 * @return - Department
	 */
	@GetMapping("/departments/{department_id}")
	public Department getDepartmentById(@PathVariable(value = "department_id", required = true) Long departmentId) {
		return departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("USR_02", "The field example is empty.", "example", "500"));
	}
}
