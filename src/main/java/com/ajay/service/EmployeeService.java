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
	public List<Employee> getAllEmployees() {
		log.info("EmployeeService.getAllEmployees()");
		return employeeRepo.findAll(Sort.by("status").descending());
	}

	/**
	 * getByID
	 * 
	 * @param id
	 * @return Employee
	 * @throws Exception
	 */
	public Employee getEmpById(Integer id) throws Exception {
		log.info("EmployeeService.getEmpById()");
		Optional<Employee> opt = employeeRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new Exception("No Data Found.");
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
		employee.setStatus(1);
		employee = employeeRepo.save(employee);
		String msg= "Employee '" + employee.getName() + "' has Added successfully.";
		return new Payload(msg, HttpStatus.OK, null);
	}

	/**
	 * update
	 * 
	 * @param employee
	 * @return String
	 * @throws Exception
	 */
	public String updateEmployee(Employee employee) throws Exception {
		log.info("EmployeeService.updateEmployee()");
		if (employee.getId() != null) {
			Optional<Employee> opt = employeeRepo.findById(employee.getId());
			if (opt.isPresent() && opt.get().getStatus() == 1) {
				employee.setStatus(1);
				employee = employeeRepo.save(employee);
				return "Employee '" + employee.getName() + "' has Updated successfully.";
			}
			throw new Exception("Data not Found.");
		}
		throw new Exception("Error Found! Please try Again!!");
	}

	/**
	 * delete (only disable status)
	 * 
	 * @param id
	 * @return String
	 * @throws Exception
	 */
	public String deleteEmployee(Integer id) throws Exception {
		log.info("EmployeeService.deleteEmployee()");
		if (id != null) {
			Optional<Employee> opt = employeeRepo.findById(id);
			if (opt.isPresent()) {
				Employee employee = opt.get();
				employee.setStatus(0);
				employee = employeeRepo.save(employee);
				return "Employee '" + employee.getName() + "' has Updated successfully.";
			}
			throw new Exception("Data not Found.");
		}
		throw new Exception("Error Found! Please try Again!!");
	}

	/**
	 * Change Status of Employee
	 * 
	 * @param id
	 * @return String
	 * @throws Exception
	 */
	public String changeStatus(Integer id) throws Exception {
		log.info("EmployeeService.changeStatus()");
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
				return "Employee '" + employee.getName() + "' has Updated Status successfully.";
			}
			throw new Exception("Data not Found.");
		}
		throw new Exception("Error Found! Please try Again!!");
	}

}
