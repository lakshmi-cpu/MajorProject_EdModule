package com.sb.crudoperation.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.crudoperation.entity.CitizenAppEntity;
import com.sb.crudoperation.entity.DcCases;

public interface CitizenApprepo extends JpaRepository<CitizenAppEntity, Serializable>{

	public DcCases save(DcCases caseentity);

}
