package com.sb.crudoperation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="DCINCOME")
@Data
public class DCIncome {
	
	@Id
	@GeneratedValue
	private Integer incomeid;
	private Long casenumber;
	private Double monthlysalary;
	private Double rentincome;
	private Double propertyincome;
	
	
	
	

}
