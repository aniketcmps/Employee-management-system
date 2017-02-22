package com.freedom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.freedom.model.Employee;
import com.freedom.model.EmployeeDao;
import com.freedom.util.CustomErrorType;
/**
 * Controller for Employee table 
 */

@RestController
@RequestMapping("/rest/employee")
public class EmployeeController {
	/**
	 * Controller for Department table 
	 */
	
	@Autowired
	private EmployeeDao employeeDao;

	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	/**
	 * Get List of Employees 
	 * This method returns all entries from employee table
	 * access URL: http://localhost:8096/rest/employee/ 
	 * RequestMethod=GET
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> findAll() {
		logger.info("Fetching list of employees ");
		List<Employee> list = employeeDao.getAll();
		if (list.isEmpty()) {
			logger.error("No entries present in employee");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	/**
	 * Get an Employee 
	 * This method returns entry from employee table corresponding to specified {id} 
	 * access URL: http://localhost:8096/rest/employee/{id} 
	 * RequestMethod=GET
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable("id") int id) {
		logger.info("Fetching Employee with id {}", id);
		Employee employee = employeeDao.getById(id);
		if (employee == null) {
			logger.error("Employee with id {} not found. ", id);
			return new ResponseEntity<>(new CustomErrorType("Employee with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	/**
	 * Delete an Employee 
	 * This method deletes entry from employee table corresponding to specified {id} 
	 * access URL: http://localhost:8096/rest/employee/{id} 
	 * RequestMethod=DELETE
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Employee with id {}", id);
		try {
			employeeDao.delete(id);
		} catch (Exception e) {
			logger.error("Unable to delete. " + e.getMessage());
			return new ResponseEntity<>(new CustomErrorType("Unable to delete." + e.getMessage()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Employee succesfully deleted!", HttpStatus.CREATED);
	}

	/**
	 * Create an Employee 
	 * This method creates and adds entry to employee table
	 * access URL: http://localhost:8096/rest/employee/create 
	 * RequestMethod=POST
	 * {"firstName":"Test", "lastName":"User", "salary":"2000", "departmentId":"101", "address":"Tempe,AZ" }
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Employee employee) {
		logger.info("Creating Employee : {}", employee);
		try {
			employeeDao.save(employee);
		} catch (Exception e) {
			logger.error("Unable to create. " + e.getMessage());
			return new ResponseEntity<>(new CustomErrorType("Unable to create." + e.getMessage()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Employee succesfully created", HttpStatus.CREATED);
	}

	/**
	 * Update an Employee 
	 * This method updates entry from employee table corresponding to specified {id} access URL:
	 * http://localhost:8096/rest/employee/{id} 
	 * RequestMethod=PUT
	 * {"firstName":"Test", "lastName":"User", "salary":"2000", "departmentId":"101", "address":"Tempe,AZ" }
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> put(@RequestBody Employee employee, @PathVariable("id") int id) {
		logger.info("Updating Employee with id {}", id);
		try {
			employee.setEmployeeId(id);
			employeeDao.update(employee);
		} catch (Exception e) {
			logger.error("Unable to update. " + e.getMessage());
			return new ResponseEntity<>(new CustomErrorType("Unable to update." + e.getMessage()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Employee entity succesfully updated", HttpStatus.CREATED);
	}

	/**
	 * Update an Employee 
	 * This method updates entry from employee table corresponding to specified {id} access URL:
	 * http://localhost:8096/rest/employee/{id} 
	 * RequestMethod=PATCH
	 * {"salary":"200" }
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> patch(@RequestBody Employee employee, @PathVariable("id") int id) {
		logger.info("Updating Employee with id {}", id);
		try {
			employee.setEmployeeId(id);
			employeeDao.patch(employee);
		} catch (Exception e) {
			logger.error("Unable to update. " + e.getMessage());
			return new ResponseEntity<>(new CustomErrorType("Unable to update." + e.getMessage()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Employee details succesfully updated", HttpStatus.CREATED);
	}

}
