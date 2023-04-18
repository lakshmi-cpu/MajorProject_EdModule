package com.sb.crudoperation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Plan_Master")
@Data
public class Plan_Master {
	
	@Id
	@GeneratedValue
	private Integer planId;
	private String planName;

}
