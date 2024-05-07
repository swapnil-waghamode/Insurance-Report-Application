package in.ashokit.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;
import in.ashokit.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

	@Autowired
	ReportService service;
	
	
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse res) throws Exception{
		
		res.setContentType("application/pdf");
		res.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
		service.exportPdf(res);
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse res) throws Exception{
		
		res.setContentType("application/octet-stream");
		
		res.addHeader("Content-Disposition", "attachment;filename=plans.xls");
		
		service.exportExcel(res);
	}
	
	@PostMapping("/search")
	public String searchHandler(@ModelAttribute("search") SearchRequest search, Model model) {
//		System.out.println(search);
		
		List<CitizenPlan> plan = service.search(search);
		model.addAttribute("plans", plan);
		
		init(model);
		
		return "index";
	}
	
	
	@GetMapping("/")
	public String getIndexPage(Model model) {
		model.addAttribute("search",new SearchRequest());
		init(model);
		return "index";
	}


	private void init(Model model) {
		
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getStatuses());
	}
}
