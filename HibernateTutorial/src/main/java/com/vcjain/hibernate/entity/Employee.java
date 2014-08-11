/**
 * 
 */
package com.vcjain.hibernate.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/*
 * Table SQL script
 * 
 * CREATE TABLE `employee` (
    `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `firstname` VARCHAR(50) NULL DEFAULT NULL,
    `lastname` VARCHAR(50) NULL DEFAULT NULL,
    `birth_date` DATE NOT NULL,
    `cell_phone` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`)
);
 * 
 * 
 */

@Entity
@Table(name="EMPLOYEE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {
 
    @Id
    @GeneratedValue
    private Long id;
     
    @Column(name="firstname")
    private String firstname;
     
    @Column(name="lastname")
    private String lastname;
     
    @Column(name="birth_date")
    private Date birthDate;
     
    @Column(name="cell_phone")
    private String cellphone;
 
    public Employee() {
         
    }
     
    public Employee(String firstname, String lastname, Date birthdate, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthdate;
        this.cellphone = phone;
         
    }
     
    // Getter and Setter methods
}
