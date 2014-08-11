/**
 * 
 */
package com.vcjain.hibernate.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="EMPLOYEEPHONE") 
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeePhone {
 
    @Id
    @GeneratedValue
    private Long id;
     
    @Column(name="firstname")
    private String firstname;
     
    @Column(name="lastname")
    private String lastname;
     
    @Column(name="birth_date")
    private Date birthDate;
     
    @OneToMany(mappedBy="empPhone") //Name of variable define in EmployeePhone. 
    private List<ContactNumber> phones;
 
    public EmployeePhone(){
    	
    }
    public EmployeePhone(String firstname, String lastname, Date birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthdate;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<ContactNumber> getPhones() {
		return phones;
	}

	public void setPhones(List<ContactNumber> phones) {
		this.phones = phones;
	}
     
    
    // Getter and Setter methods
}
