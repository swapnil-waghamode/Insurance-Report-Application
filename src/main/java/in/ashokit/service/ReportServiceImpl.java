package in.ashokit.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.entity.repo.CitizenPlanRepo;
import in.ashokit.request.SearchRequest;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo repo;
	
	
	@Override
	public List<String> getPlanNames() {
		
		return repo.getPlanNames();
	}

	@Override
	public List<String> getStatuses() {
	
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		
		CitizenPlan cp = new CitizenPlan();
		
		if( null != request.getPlanName() && !"".equals(request.getPlanName()) ) {
			cp.setPlanName(request.getPlanName());
		}
		
		if(null != request.getPlanStatus()  && !"".equals(request.getPlanStatus())) {
			cp.setPlanStatus(request.getPlanStatus());
		}
		
		if( null !=request.getGender() && !"".equals(request.getGender() )) {
			cp.setGender(request.getGender());
		}
		
		if(null != request.getStartDate() &&   !"".equals(request.getStartDate() )) {
			String startDate = request.getStartDate();
			
		    DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    
		    LocalDate ld = LocalDate.parse(startDate, d);
		    cp.setPlanStartDate(ld);
		}
		
		if(null != request.getEndDate() &&   !"".equals(request.getEndDate() )) {
			String endDate = request.getEndDate();
			
		    DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    
		    LocalDate ld = LocalDate.parse(endDate, d);
		    cp.setPlanEndDate(ld);
		}
		
		
		return repo.findAll(Example.of(cp));
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}
