package com.sb.crudoperation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="DCCASES")
@Data
public class DcCases {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long casenumber;
	private Integer planid;
	private Integer appid;

}
