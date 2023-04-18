package com.sb.crudoperation.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sb.crudoperation.entity.DCIncome;
import com.sb.crudoperation.entity.EducationDetails;

@Repository
public interface EducationDetailsRepo extends JpaRepository<EducationDetails, Serializable>{
	
	@Query("from EducationDetails e where e.casenumber = :casenumber")
	public EducationDetails findByCaseNumber(Long casenumber);
	

}
