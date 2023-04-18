package com.sb.crudoperation.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.crudoperation.binding.EligResponse;
import com.sb.crudoperation.entity.CitizenAppEntity;
import com.sb.crudoperation.entity.CoTrgEntity;
import com.sb.crudoperation.entity.DCIncome;
import com.sb.crudoperation.entity.DCKids;
import com.sb.crudoperation.entity.DcCases;
import com.sb.crudoperation.entity.EdEligDtlsEntity;
import com.sb.crudoperation.entity.EducationDetails;
import com.sb.crudoperation.entity.Plan_Master;
import com.sb.crudoperation.repo.CitizenApprepo;
import com.sb.crudoperation.repo.EdEligRepository;
import com.sb.crudoperation.repo.CoTriggerRepository;
import com.sb.crudoperation.repo.DCIncomeRepo;
import com.sb.crudoperation.repo.DCKidsRepo;
import com.sb.crudoperation.repo.DcCasesrepo;
import com.sb.crudoperation.repo.EducationDetailsRepo;
import com.sb.crudoperation.repo.PlanMasterRepo;

@Service
public class EdServiceImpl implements EdService{

	@Autowired
	private DcCasesrepo dcCaseRepo;
	
	@Autowired
	private PlanMasterRepo planRepo;
	
	@Autowired
	private DCIncomeRepo incomeRepo;
	
	@Autowired
	private DCKidsRepo kidRepo;
	
	
	@Autowired
	private CitizenApprepo appRepository;
	
	@Autowired
	private EducationDetailsRepo educationRepo;
	
	// to insert data into trigger table and eligbility table
	
	@Autowired
	private CoTriggerRepository coTrgRepo;
	
	@Autowired
	private EdEligRepository edEligRepo;
	
	boolean noKidsFlag=false;
	boolean ageFlag=true;
	
	
	@Override
	public EligResponse determineEligibility(Long caseNum) {
		// TODO Auto-generated method stub
		
		EligResponse response=new EligResponse();
		
		Integer planId=null;
		String planName= null;
		Integer appId=null;
		
		
		Optional<DcCases> dcCaseEntity= dcCaseRepo.findById(caseNum);
		
		if(dcCaseEntity.isPresent())
		{
		planId=	dcCaseEntity.get().getPlanid();
		 appId= dcCaseEntity.get().getAppid();
			
		}
		
		
        Optional<Plan_Master> planEntity= planRepo.findById(planId);
		
		if(planEntity.isPresent())
		{
			planName= planEntity.get().getPlanName();
		}
		//from appid we are getting citizen data
	    Optional<CitizenAppEntity> citizenAppEntity=appRepository.findById(appId);
	    //getting the dob of the citizen
	    CitizenAppEntity citizenApp=citizenAppEntity.get();
		
		
		DCIncome income=incomeRepo.findByCaseNumber(caseNum);
		
		List<DCKids> kids = kidRepo.findByCaseNumber(caseNum);
		
		if("SNAP".equals(planName))
		{
			if(income.getMonthlysalary()>300)
			{
				response.setPlan_status("Denied");
				response.setDenial_reason("High Income");
			}else
			{
				
			}
			
			
		}
		else if("CCAP".equals(planName)) {
			
			
			
			if(!kids.isEmpty())
			{
			kids.forEach(kid ->{
			
					LocalDate dob=kid.getKiddob();
					LocalDate today=LocalDate.now();
			
					Period p=Period.between(dob, today);
					int year=p.getYears();
					
					if(year > 16)
					{
						ageFlag=false;
					}
			
			});
			
			
		}else
		{
			response.setDenial_reason("no kids available");
			noKidsFlag=true;
		}
			
			
			if(income.getMonthlysalary()> 300  )
			{
				
				response.setDenial_reason("High Income  ");
			}
			
			if(noKidsFlag && income.getMonthlysalary()>300)
			{
				response.setDenial_reason("high income + nokids");
			}
			
			if(!ageFlag)
			{
				response.setDenial_reason("Kids age is greater than 16");
			}
			
			if(income.getMonthlysalary()>300 && !ageFlag)
			{
				response.setDenial_reason("high income + kid age > 16");
			}
			
			
		else if("Medicaid".equals(planName)) {
			
			
			Double salaryIncome= income.getMonthlysalary();
			Double rentIncome=income.getRentincome();
			Double propertyIncome=income.getPropertyincome();
			
			if(salaryIncome>300)
			{
				response.setDenial_reason("High Inccome");
				
			}
			if(rentIncome>0)
			{
				response.setDenial_reason("Rential Income available");
			}
			if(propertyIncome>0)
			{
				response.setDenial_reason("Property Income available");
			}
			
			if(rentIncome >0 && propertyIncome>0)
			{
				response.setDenial_reason("Rental+property Income available");
			}
			if(salaryIncome>300 && propertyIncome>0 && rentIncome>0)
			{
				response.setDenial_reason(" rental + property+ salary income");
			}
		}
		}
		else if("Medicare".equals(planName)) {
			
			LocalDate dob=citizenApp.getDob();
			LocalDate now=LocalDate.now();
			
			Period between=Period.between(dob, now);
			int years=between.getYears();
			
			if(years<65)
			{
				response.setDenial_reason("Age < 65");
			}
			
			
			
		}
		else if("RIW".equals(planName)) {
			
			EducationDetails educationEntity=educationRepo.findByCaseNumber(caseNum);
			Integer graduationYear=educationEntity.getGraduationyear();
			if(graduationYear <=0)
			{
				response.setDenial_reason("Not Graunted");
			}
			if(income.getMonthlysalary()>0)
			{
				response.setDenial_reason("Aleady an Employee");
			}
			
			
		}
		
		//now we need to set the data required in the ui table
		
		response.setPlan_name(planName);
		if(response.getDenial_reason()!=null)
		{
			response.setPlan_status("DENIED");
		}
		else
		{
			response.setPlan_status("APPROVED");
			response.setPlan_start_date(LocalDate.now().plusDays(1));
			response.setPlan_end_date(LocalDate.now().plusMonths(3));
			response.setBenefit_amt(350.00);
		}
		
		//we got the response data with that we need enter data to edeligdtlsentity
		
		EdEligDtlsEntity edEntity=new EdEligDtlsEntity();
		BeanUtils.copyProperties(response, edEntity);
		
		edEligRepo.save(edEntity);
		
		//insert record into cotrigger table
		
		CoTrgEntity coEntity=new CoTrgEntity();
		coEntity.setCaseNum(caseNum);
		//by default we are setting status as pending
		coEntity.setTrgStatus("pending");
		
		coTrgRepo.save(coEntity);
		
		
		return response;
	}
	

}
