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

import com.vcjain.hibernate.entity.ContactNumber;
import com.vcjain.hibernate.entity.Employee;
import com.vcjain.hibernate.entity.EmployeePhone;

/**
 * @author vcjain
 *
 */
public class EmployeePhoneManager {

	EntityManagerFactory emf = null;
	EntityManager em = null;

	public EmployeePhoneManager() {
		emf = Persistence.createEntityManagerFactory("tutorial");
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {
		EmployeePhoneManager o = new EmployeePhoneManager();
		try {
			o.addEmployee();
			o.em = o.emf.createEntityManager();
			o.getEmployee();
			o.getEmployeeUsingCriteriaQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			o.emf.close();
		}
	}

	public void addEmployee() {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
				EmployeePhone emp = new EmployeePhone("Vikash", "Jain", new Date(
						2014, 8, 12));
				em.persist(emp);
				ContactNumber number = new ContactNumber("Mobile", 9425912135l, emp);
				em.persist(number);
				number = new ContactNumber("Landline", 7319999999l, emp);
				em.persist(number);
				em.flush();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	public void getEmployee() {
		EmployeePhone empPhone = em.find(EmployeePhone.class, new Long(3));
		System.out.println(empPhone.getPhones());
		System.out.println("Employee fetched!!!!!!");
	}

	public void getEmployeeUsingCriteriaQuery() {
		try {
			em = emf.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<EmployeePhone> q = cb
					.createQuery(EmployeePhone.class);
			Root<EmployeePhone> e = q.from(EmployeePhone.class);
			Root<ContactNumber> contactNumber = q.from(ContactNumber.class);
			q.select(e)
					.where(cb.equal(e.get("id").as(Long.class), new Long(3)));

			final TypedQuery<EmployeePhone> query = em.createQuery(q);
			List<EmployeePhone> resultSet = query.getResultList();
			List<ContactNumber> phones = resultSet.get(0).getPhones();
			for (ContactNumber contactNumber2 : phones) {
				System.out.println("Contact Number "
						+ contactNumber2.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
