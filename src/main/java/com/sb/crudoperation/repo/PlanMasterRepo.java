package com.sb.crudoperation.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.crudoperation.entity.Plan_Master;

public interface PlanMasterRepo extends JpaRepository<Plan_Master, Serializable>{

}
