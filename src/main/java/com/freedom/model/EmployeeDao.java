package com.freedom.model;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(Employee employee) {
		getSession().save(employee);
		return;
	}

	public void delete(Employee employee) {
		getSession().delete(employee);
		return;
	}

	public void delete(int employeeID) {
		Employee employee = new Employee(employeeID);
		getSession().delete(employee);
		return;
	}

	public int patch(Employee employee) {
		Employee ref = getById(employee.getEmployeeId());
		if (employee.getFirstName() != null) {
			ref.setFirstName(employee.getFirstName());
		}
		if (employee.getLastName() != null) {
			ref.setLastName(employee.getLastName());
		}
		if(employee.getAddress()!=null){
			ref.setAddress(employee.getAddress());
		}
		if(employee.getSalary()>0){
			ref.setSalary(employee.getSalary());
		}
		if(employee.getDepartmentId()>0){
			ref.setDepartmentId(employee.getDepartmentId());
		}
		return update(ref);
	}

	public int update(Employee employee) {
		Query query = getSession().createSQLQuery(
				"update Employee set firstName=:firstName, lastName=:lastName, address=:address, salary=:salary, "
						+ "departmentId=:departmentId where employeeId=:employeeId");
		query.setParameter("firstName", employee.getFirstName());
		query.setParameter("lastName", employee.getLastName());
		query.setParameter("employeeId", employee.getEmployeeId());
		query.setParameter("address", employee.getAddress());
		query.setParameter("salary", employee.getSalary());
		query.setParameter("departmentId", employee.getDepartmentId());
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAll() {
		return (List<Employee>) getSession().createSQLQuery("SELECT * FROM Employee").addEntity(Employee.class).list();
	}

	public Employee getById(int employeeId) {
		return (Employee) getSession().get(Employee.class, employeeId);
	}
}
