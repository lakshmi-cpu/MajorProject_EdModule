package com.sb.crudoperation.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="DCKIDS")
@Data
public class DCKids {
	
	@Id
	@GeneratedValue
	private Integer kidid;
	private String kidname;
	private LocalDate kiddob;
	private Long ssn;
	private String kidgender;
	
	private Long casenumber;

}
