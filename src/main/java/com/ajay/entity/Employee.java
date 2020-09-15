package com.ajay.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emp_tab_new")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	@NonNull
	private Integer id;
	@Column(name = "emp_name")
	private String name;
	@Column(name = "emp_dept")
	private String dept;
	@Column(name = "emp_salary")
	private Double salary;
	@Column(name = "status")
	private Integer status;

}
