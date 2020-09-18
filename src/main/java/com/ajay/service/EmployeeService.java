package com.ajay.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ajay.entity.Employee;
import com.ajay.model.Payload;
import com.ajay.repo.EmployeeRepo;

@Service
public class EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepo employeeRepo;

	/**
	 * getAll
	 * 
	 * @return List<Employee>
	 */
	public Payload getAllEmployees() {
		log.info("EmployeeService.getAllEmployees()");
		List<Employee> employees = employeeRepo.findAll(Sort.by("status").descending());
		if (!employees.isEmpty()) {
			return new Payload("Success", HttpStatus.OK, employees);
		}
		return new Payload("Error", HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	/**
	 * getByID
	 * 
	 * @param id
	 * @return Employee
	 * @throws Exception
	 */
	public Payload getEmpById(Integer id) throws Exception {
		log.info("EmployeeService.getEmpById()");
		Optional<Employee> opt = employeeRepo.findById(id);
		if (opt.isPresent()) {
			Employee employee = opt.get();
			return new Payload("Success", HttpStatus.OK, employee);
		} else {
			return new Payload("Success", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	/**
	 * save (with enable status)
	 * 
	 * @param employee
	 * @return String
	 */
	public Payload saveEmployee(Employee employee) {
		log.info("EmployeeService.saveEmployee()");
		Payload payload = null;
		employee.setStatus(1);
		try {
			employee = employeeRepo.save(employee);
			String msg = "Employee '" + employee.getName() + "' has Added successfully.";
			payload = new Payload(msg, HttpStatus.OK, null);
		} catch (Exception e) {
			payload = new Payload("Error", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return payload;
	}

	/**
	 * update
	 * 
	 * @param employee
	 * @return String
	 * @throws Exception
	 */
	public Payload updateEmployee(Employee employee) throws Exception {
		log.info("EmployeeService.updateEmployee()");
		if (employee.getId() != null) {
			Optional<Employee> opt = employeeRepo.findById(employee.getId());
			if (opt.isPresent() && opt.get().getStatus() == 1) {
				employee.setStatus(1);
				employee = employeeRepo.save(employee);
				String msg = "Employee '" + employee.getName() + "' has Updated successfully.";
				return new Payload(msg, HttpStatus.OK, null);
			}
			log.error("Data not Found.");
			return new Payload("Data not Found.", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		log.error("Error Found! Please try Again!!");
		return new Payload("Error Found! Please try Again!!", HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	/**
	 * delete (only disable status)
	 * 
	 * @param id
	 * @return String
	 * @throws Exception
	 */
	public Payload deleteEmployee(Integer id) throws Exception {
		log.info("EmployeeService.deleteEmployee()");
		String msg = "";
		if (id != null) {
			Optional<Employee> opt = employeeRepo.findById(id);
			if (opt.isPresent()) {
				Employee employee = opt.get();
				employee.setStatus(0);
				employee = employeeRepo.save(employee);
				msg = "Employee '" + employee.getName() + "' has Updated successfully.";
				return new Payload(msg, HttpStatus.OK, null);
			}
			msg = "Data not Found.";
			return new Payload(msg, HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		msg = "Error Found! Please try Again!!";
		return new Payload(msg, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

	/**
	 * Change Status of Employee
	 * 
	 * @param id
	 * @return String
	 * @throws Exception
	 */
	public Payload changeStatus(Integer id) throws Exception {
		log.info("EmployeeService.changeStatus()");
		String msg = "";
		if (id != null) {
			Optional<Employee> opt = employeeRepo.findById(id);
			if (opt.isPresent()) {
				Employee employee = opt.get();
				if (employee.getStatus() == 0) {
					employee.setStatus(1);
				} else if (employee.getStatus() == 1) {
					employee.setStatus(0);
				}
				employee = employeeRepo.save(employee);
				msg = "Employee '" + employee.getName() + "' has Updated Status successfully.";
				return new Payload(msg, HttpStatus.OK, null);
			}
			msg = "Data not Found.";
			return new Payload(msg, HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		msg = "Error Found! Please try Again!!";
		return new Payload(msg, HttpStatus.INTERNAL_SERVER_ERROR, null);
	}

}
