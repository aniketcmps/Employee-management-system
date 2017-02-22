package com.freedom.model;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * DAO for Department table. Performs all the basic functions required. 
 */

@Repository
@Transactional
public class DepartmentDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(Department department) {
		getSession().save(department);
		return;
	}

	public void delete(Department department) {
		getSession().delete(department);
		return;
	}

	public void delete(int departmentID) {
		Department department = new Department(departmentID);
		getSession().delete(department);
		return;
	}

	public int patch(Department department) {
		Department ref=getById(department.getDepartmentId());
		if(department.getName()!=null){
			ref.setName(department.getName());
		}
		return update(ref);
	}

	public int update(Department department){
		Query query = getSession().createSQLQuery("update Department set name=:name where departmentId=:departmentId");
		query.setParameter("name",department.getName());
		query.setParameter("departmentId", department.getDepartmentId());
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Department> getAll() {
		return (List<Department>) getSession().createSQLQuery("SELECT * FROM Department").addEntity(Department.class).list();
	}

	public Department getById(int departmentId) {
		return (Department) getSession().get(Department.class, departmentId);
	}
}
