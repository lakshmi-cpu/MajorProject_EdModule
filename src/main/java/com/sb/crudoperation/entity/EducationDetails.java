package com.sb.crudoperation.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="EDUCATIONDETAILS")
@Data
public class EducationDetails {
	
	@Id
	@GeneratedValue
	private Integer eduid;
	private Long casenumber;
	private String highestdegree;
	private Integer graduationyear;
	private String universityname;
	

}
