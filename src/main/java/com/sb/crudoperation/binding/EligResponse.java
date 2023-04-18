package com.sb.crudoperation.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EligResponse {
	
	
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
