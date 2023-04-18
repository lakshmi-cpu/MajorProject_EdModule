package com.sb.crudoperation.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sb.crudoperation.entity.DCKids;

@Repository
public interface DCKidsRepo extends JpaRepository<DCKids, Serializable>{
	
	@Query("from DCKids k where k.casenumber = :casenumber")
	public List<DCKids> findByCaseNumber(Long casenumber);

}
