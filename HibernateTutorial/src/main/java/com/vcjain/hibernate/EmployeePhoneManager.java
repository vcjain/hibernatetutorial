/**
 * 
 */
package com.vcjain.hibernate;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.vcjain.hibernate.entity.ContactNumber;
import com.vcjain.hibernate.entity.EmployeePhone;

/**
 * @author vcjain
 *
 */
public class EmployeePhoneManager {
	
	EntityManagerFactory emf = null;
	EntityManager em = null;
	
	public EmployeePhoneManager(){
		emf = Persistence.createEntityManagerFactory("tutorial");
		em = emf.createEntityManager();
	}
	public static void main(String[] args) {
		EmployeePhoneManager o = new EmployeePhoneManager();
		try{
			o.addEmployee();
			o.getEmployee();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			o.em.close();
			o.emf.close();
		}
	}
	
	public void addEmployee(){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		EmployeePhone emp = new EmployeePhone("Vikash", "Jain",new Date(2014,8,12));
		em.persist(emp);
		ContactNumber number = new ContactNumber("Mobile",9425912135l,emp);
		em.persist(number);
		number = new ContactNumber("Landline",7319999999l,emp);
		em.persist(number);
		em.flush();
		tx.commit();
	}

	public void getEmployee(){
		EmployeePhone empPhone = em.find(EmployeePhone.class, new Long(3));
		System.out.println(empPhone.getPhones());
		System.out.println("Employee fetched!!!!!!");
	}
}
