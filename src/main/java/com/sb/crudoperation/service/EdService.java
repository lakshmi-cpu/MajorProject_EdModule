package com.sb.crudoperation.service;

import org.springframework.stereotype.Service;

import com.sb.crudoperation.binding.EligResponse;

@Service
public interface EdService {
	
	public EligResponse determineEligibility(Long caseNum);

}
