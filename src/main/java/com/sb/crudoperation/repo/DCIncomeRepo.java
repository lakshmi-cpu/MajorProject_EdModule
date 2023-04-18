package com.sb.crudoperation.repo;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sb.crudoperation.entity.DCIncome;

@Repository
public interface DCIncomeRepo extends JpaRepository<DCIncome, Long>{

	@Query("from DCIncome d where d.casenumber = :casenumber")
	DCIncome findByCaseNumber(Long casenumber);
	
	//we have written our own methods in our repo classes (in summary method serviceimpl)
//	public DCIncome findBycaseNumber(Long casenumber);
	
//	public DCIncome findById(Long casenumber);

//	public DCIncome findByCaseNumber(Long casenumber);

}
