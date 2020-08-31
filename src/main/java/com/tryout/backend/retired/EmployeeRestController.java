/*package com.tryout.backend.retired;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeDAO  employeeDAO;
	
	// quick and dirty: inject employee DAO
	@Autowired
	public EmployeeRestController(EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}
	
	//expose "/employees" and return list of employees
	@GetMapping("/employees") 
	public List<Employee> findAll() throws Exception{
		
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		System.out.println("REQUEST RECEIVED!!!!!");
		return employeeDAO.findAll();
	}
	
}*/
