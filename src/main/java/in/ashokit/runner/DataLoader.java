package in.ashokit.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.entity.repo.CitizenPlanRepo;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	CitizenPlanRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repo.deleteAll();
		
//		Cash Plan 
		CitizenPlan cp = new CitizenPlan();
		
		cp.setCitizenName("John");
		cp.setGender("Male");
		cp.setPlanName("Cash");
		cp.setPlanStatus("Approved");
		cp.setPlanStartDate(LocalDate.now());
		cp.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp.setBenefitAmt(5000.00);
		
//		Cash plan
        CitizenPlan cp2 = new CitizenPlan();
		
		cp2.setCitizenName("Smith");
		cp2.setGender("Male");
		cp2.setPlanName("Cash");
		cp2.setPlanStatus("Denied");
		cp2.setDenialReason("Rental Income");
		
		
//		Cash Plan
		CitizenPlan cp3 = new CitizenPlan();
		
		cp3.setCitizenName("Catee");
		cp3.setGender("Female");
		cp3.setPlanName("Cash");
		cp3.setPlanStatus("Terminated");
		cp3.setPlanStartDate(LocalDate.now().minusMonths(4));
		cp3.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp3.setBenefitAmt(5000.00);
		cp3.setTerminatedDate(LocalDate.now());
		cp3.setTerminationRsn("Employeed");
		
//		***********************************************************************
		
//		Food Plan 
		CitizenPlan cp4 = new CitizenPlan();
		
		cp4.setCitizenName("Ron");
		cp4.setGender("Male");
		cp4.setPlanName("Food");
		cp4.setPlanStatus("Approved");
		cp4.setPlanStartDate(LocalDate.now());
		cp4.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp4.setBenefitAmt(4000.00);
		
//		food plan
        CitizenPlan cp5 = new CitizenPlan();
		
        cp5.setCitizenName("Shogun");
        cp5.setGender("Male");
        cp5.setPlanName("Food");
        cp5.setPlanStatus("Denied");
        cp5.setDenialReason("Property Income");
		
		
//		food Plan
		CitizenPlan cp6 = new CitizenPlan();
		
		cp6.setCitizenName("Morne");
		cp6.setGender("Female");
		cp6.setPlanName("Food");
		cp6.setPlanStatus("Terminated");
		cp6.setPlanStartDate(LocalDate.now().minusMonths(4));
		cp6.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp6.setBenefitAmt(4000.00);
		cp6.setTerminatedDate(LocalDate.now());
		cp6.setTerminationRsn("Employeed");
		
//	**********************************************************************
		
//		Medical Plan 
		CitizenPlan cp7 = new CitizenPlan();
		
		cp7.setCitizenName("Macurg");
		cp7.setGender("Male");
		cp7.setPlanName("Medical");
		cp7.setPlanStatus("Approved");
		cp7.setPlanStartDate(LocalDate.now());
		cp7.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp7.setBenefitAmt(7000.00);
		
//		Medical
        CitizenPlan cp8 = new CitizenPlan();
		
        cp8.setCitizenName("Manas");
        cp8.setGender("Male");
        cp8.setPlanName("Medical");
        cp8.setPlanStatus("Denied");
        cp8.setDenialReason("Property Income");
        
//		Medical
		CitizenPlan cp9 = new CitizenPlan();
		
		cp9.setCitizenName("Jenny");
		cp9.setGender("Female");
		cp9.setPlanName("Medical");
		cp9.setPlanStatus("Terminated");
		cp9.setPlanStartDate(LocalDate.now().minusMonths(4));
		cp9.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp9.setBenefitAmt(7000.00);
		cp9.setTerminatedDate(LocalDate.now());
		cp9.setTerminationRsn("Govt Job");
		
//	**********************************************************************
		
//		Employment Plan 
		CitizenPlan cp10 = new CitizenPlan();
		
		cp10.setCitizenName("Marsh");
		cp10.setGender("Male");
		cp10.setPlanName("Employment");
		cp10.setPlanStatus("Approved");
		cp10.setPlanStartDate(LocalDate.now());
		cp10.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp10.setBenefitAmt(7000.00);
		
//		Medical
        CitizenPlan cp11 = new CitizenPlan();
		
        cp11.setCitizenName("Mithun");
        cp11.setGender("Male");
        cp11.setPlanName("Employment");
        cp11.setPlanStatus("Denied");
        cp11.setDenialReason("Property Income");
        
//		Medical
		CitizenPlan cp12 = new CitizenPlan();
		
		cp12.setCitizenName("Alexa");
		cp12.setGender("Female");
		cp12.setPlanName("Employment");
		cp12.setPlanStatus("Terminated");
		cp12.setPlanStartDate(LocalDate.now().minusMonths(4));
		cp12.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp12.setBenefitAmt(7000.00);
		cp12.setTerminatedDate(LocalDate.now());
		cp12.setTerminationRsn("Govt Job");
		
		
		List<CitizenPlan> planList = Arrays.asList(cp,cp2,cp3,cp4,cp5,cp6,cp7,cp8,cp9,cp10,cp11,cp12);
		
		
		repo.saveAll(planList);
		
	}

}
