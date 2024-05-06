package in.ashokit.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
		
		if(request.getPlanName() != null && request.getPlanName() != "") {
			cp.setPlanName(request.getPlanName());
		}
		
		if(request.getPlanStatus() != null && request.getPlanStatus() != "") {
			cp.setPlanStatus(request.getPlanStatus());
		}
		
		if(request.getGender() != null && request.getGender() != "") {
			cp.setGender(request.getGender());
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
