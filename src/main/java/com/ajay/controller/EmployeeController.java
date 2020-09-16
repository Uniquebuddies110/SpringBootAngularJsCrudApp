package com.ajay.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajay.entity.Employee;
import com.ajay.model.Payload;
import com.ajay.service.EmployeeService;

@Controller
@RequestMapping("/emp/api")
public class EmployeeController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	/**
	 * getAll
	 * 
	 * @return List of Employees
	 */
	@GetMapping("/getAll")
	@ResponseBody
	public List<Employee> getAllEmps() {
		log.info("EmployeeController.getAllEmps()");
		return employeeService.getAllEmployees();
	}

	/**
	 * getByID
	 * 
	 * @param id
	 * @return Employee
	 * @throws Exception
	 */
	@GetMapping("/getAll/{id}")
	@ResponseBody
	public Employee getAllEmps(@PathVariable Integer id) throws Exception {
		log.info("EmployeeController.getAllEmps()");
		return employeeService.getEmpById(id);
	}

	/**
	 * save
	 * 
	 * @param employee
	 * @return String
	 */
	@PostMapping("/save")
	@ResponseBody
	public Payload saveEmployee(@RequestBody Employee employee) {
		log.info("EmployeeController.saveEmployee()");
		return employeeService.saveEmployee(employee);
	}

	/**
	 * update
	 * 
	 * @param employee
	 * @return String
	 * @throws Exception
	 */
	@PutMapping("/update")
	@ResponseBody
	public String updateEmployee(@RequestBody Employee employee) throws Exception {
		log.info("EmployeeController.updateEmployee()");
		return employeeService.updateEmployee(employee);
	}

	
	@PutMapping("/changeStatus/{id}")
	@ResponseBody
	public String changeStatusOfEmployee(@PathVariable Integer id) throws Exception {
		log.info("EmployeeController.updateEmployee()");
		return employeeService.changeStatus(id);
	}

	/**
	 * delete
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteEmployee(@PathVariable Integer id) throws Exception {
		log.info("EmployeeController.deleteEmployee()");
		return employeeService.deleteEmployee(id);
	}

//	@RequestMapping("/")
//	public String homePage() {
//		return "/page/Home";
//	}
	
//	@RequestMapping("/index")
//	public String indexView() {
//		return "Index";
//	}

	@RequestMapping("/addNewEmp")
	public String addNewEmpPage() {
		return "/page/emp/reg";
	}

	@RequestMapping("/displayAll")
	public String getAllEmpsPage() {
		return "/page/emp/view";
	}

}
