package com.sb.crudoperation.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="ELIG_DTLS")
@Data
public class EdEligDtlsEntity {
	
	@Id
	@GeneratedValue
	private Integer edTraceId;
	private Long case_num;
	private String holderName;
	private Long holderSsn;
	private String plan_name;
	private String plan_status;
	private LocalDate plan_start_date;
	private LocalDate plan_end_date;
	private Double benefit_amt;
	private String denial_reason;
	

}
