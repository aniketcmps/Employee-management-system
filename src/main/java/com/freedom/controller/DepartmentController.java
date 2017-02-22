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

import com.freedom.model.Department;
import com.freedom.model.DepartmentDao;
import com.freedom.util.CustomErrorType;

@RestController
@RequestMapping("/rest/department")
public class DepartmentController {

	@Autowired
	private DepartmentDao departmentDao;

	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	/**
	 * Get List of Departments 
	 * This method returns all entries from department table 
	 * access URL: http://localhost:8096/rest/department/
	 * RequestMethod=GET
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> findAll() {
		logger.info("Fetching list of department ");
		List<Department> list = departmentDao.getAll();
		if (list.isEmpty()) {
			logger.error("No entries present in department");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Department>>(list, HttpStatus.OK);
	}

	/**
	 * Get a Department 
	 * This method returns entry from department table corresponding to specified {id} 
	 * access URL: http://localhost:8096/rest/department/{id} 
	 * RequestMethod=GET
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable("id") int id) {
		logger.info("Fetching Department with id {}", id);
		Department employee = departmentDao.getById(id);
		if (employee == null) {
			logger.error("Department with id {} not found. ", id);
			return new ResponseEntity<>(new CustomErrorType("Department with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Department>(employee, HttpStatus.OK);
	}

	/**
	 * Delete a Department 
	 * This method deletes entry from department table corresponding to specified {id} 
	 * access URL: http://localhost:8096/rest/department/{id} 
	 * RequestMethod=DELETE
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting Department with id {}", id);
		try {
			departmentDao.delete(id);
		} catch (Exception e) {
			logger.error("Unable to delete. " + e.getMessage());
			return new ResponseEntity<>(new CustomErrorType("Unable to delete." + e.getMessage()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Department succesfully deleted!", HttpStatus.CREATED);
	}

	/**
	 * Create a Department 
	 * This method creates and adds entry to department table 
	 * access URL: http://localhost:8096/rest/department/create
	 * RequestMethod=POST {"name":"HR" }
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Department department) {
		logger.info("Creating Department : {}", department);
		try {
			departmentDao.save(department);
		} catch (Exception e) {
			logger.error("Unable to create. " + e.getMessage());
			return new ResponseEntity<>(new CustomErrorType("Unable to create." + e.getMessage()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Department succesfully created", HttpStatus.CREATED);
	}

	/**
	 * Update a Department 
	 * This method updates entry from department table corresponding to specified {id} 
	 * access URL:http://localhost:8096/rest/department/{id} 
	 * RequestMethod=PUT
	 * {"name":"Finance" }
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> put(@RequestBody Department department, @PathVariable("id") int id) {
		logger.info("Updating Department with id {}", id);
		try {
			department.setDepartmentId(id);
			departmentDao.update(department);
		} catch (Exception e) {
			logger.error("Unable to update. " + e.getMessage());
			return new ResponseEntity<>(new CustomErrorType("Unable to update." + e.getMessage()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Department entity succesfully updated", HttpStatus.CREATED);
	}

	/**
	 * Update a Department 
	 * This method updates entry from department table corresponding to specified {id} 
	 * access URL:http://localhost:8096/rest/department/{id} 
	 * RequestMethod=PATCH
	 * {"name":"Finance" }
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> patch(@RequestBody Department department, @PathVariable("id") int id) {
		logger.info("Updating Department with id {}", id);
		try {
			department.setDepartmentId(id);
			departmentDao.patch(department);
		} catch (Exception e) {
			logger.error("Unable to update. " + e.getMessage());
			return new ResponseEntity<>(new CustomErrorType("Unable to update." + e.getMessage()), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Department details succesfully updated", HttpStatus.CREATED);
	}

}
