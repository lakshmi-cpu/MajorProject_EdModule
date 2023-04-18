package com.sb.crudoperation.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.crudoperation.entity.DcCases;




public interface DcCasesrepo extends JpaRepository<DcCases, Serializable>{
	
	

}
