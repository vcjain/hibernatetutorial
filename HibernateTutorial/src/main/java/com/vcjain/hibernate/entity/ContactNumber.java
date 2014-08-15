/**
 * 
 */
package com.vcjain.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/*
 * TABLE SQL Script
 * 
 * CREATE TABLE `ContactNumber`(
`contact_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
`type` VARCHAR(50),
`number` BIGINT(10),
`employee_id` BIGINT(20),
PRIMARY KEY (`contact_id`)
)
 * 
 */
/**
 * @author vcjain
 *
 */
@Entity
@Table(name="contactnumber")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContactNumber {

	@Id
    @GeneratedValue
    //@GeneratedValue(strategy=GenerationType.)
    /*
     * GenerationType.AUTO default
     * GenerationType.IDENTITY
     * GenerationType.SEQUENCE
     * GenerationType.TABLE
     */
    private Long contact_id;
     
    @Column(name="type")
    private String contactType;
    
    @Column(name="number")
    private Long number;
    
    @ManyToOne
    @JoinColumn(name="employee_id")
    private EmployeePhone empPhone;
    
    public ContactNumber(){
    }
    
    
    public ContactNumber(String type, Long number,EmployeePhone phone){
    	this.contactType = type;
    	this.number = number;
    	this.empPhone = phone;
    }
    
    public String toString(){
    	return this.contactType+" number is "+this.number;
    }
}
