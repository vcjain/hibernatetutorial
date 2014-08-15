/**
 * 
 */
package com.vcjain.hibernate;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.vcjain.hibernate.entity.Employee;

/**
 * @author vcjain
 *
 */
public class EmployeeManager {

	EntityManagerFactory emf = null;
	EntityManager em = null;

	public EmployeeManager() {
		emf = Persistence.createEntityManagerFactory("tutorial");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		EmployeeManager o = new EmployeeManager();
		try{
			o.addEmployee();
			
			//Caching functionality
			o.getEmployee(new Long(2));
			o.getEmployee(new Long(3));
			
			//Using Criteria
			o.getEmployeeUsingCriteriaQuery();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			o.emf.close();
		}
	}

	public void addEmployee() {
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Employee emp = new Employee("Vikash", "Jain",
					new Date(2014, 8, 12), "09425912135");
			em.persist(emp);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public void getEmployee(Long ID){
		try{
			em = emf.createEntityManager();
			Employee emp = em.find(Employee.class, ID);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
	}
	
	public void getEmployeeUsingCriteriaQuery(){
		try{
			em = emf.createEntityManager();
			 CriteriaBuilder cb = em.getCriteriaBuilder();
			 
			  CriteriaQuery<Employee> q = cb.createQuery(Employee.class);
			  Root<Employee> e = q.from(Employee.class);
			  q.select(e).where(cb.gt(e.get("id").as(Long.class), new Long(2)));
			  
			  final TypedQuery<Employee> query = em.createQuery(q);
			  List<Employee> resultSet = query.getResultList();
			  System.out.println(resultSet.get(0).getFirstname());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
	}
}
