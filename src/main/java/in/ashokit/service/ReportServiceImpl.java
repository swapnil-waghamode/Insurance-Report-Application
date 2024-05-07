package in.ashokit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.tree.ExpandVetoException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.entity.repo.CitizenPlanRepo;
import in.ashokit.request.SearchRequest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

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

		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			cp.setPlanName(request.getPlanName());
		}

		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			cp.setPlanStatus(request.getPlanStatus());
		}

		if (null != request.getGender() && !"".equals(request.getGender())) {
			cp.setGender(request.getGender());
		}

		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();

			DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate ld = LocalDate.parse(startDate, d);
			cp.setPlanStartDate(ld);
		}

		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();

			DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate ld = LocalDate.parse(endDate, d);
			cp.setPlanEndDate(ld);
		}

		return repo.findAll(Example.of(cp));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {

		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("plans-data");
		Row headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amount");

		List<CitizenPlan> records = repo.findAll();

		int dataRowIndex = 1;

		for (CitizenPlan p : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(p.getCitizenId());
			dataRow.createCell(1).setCellValue(p.getCitizenName());
			dataRow.createCell(2).setCellValue(p.getPlanName());
			dataRow.createCell(3).setCellValue(p.getPlanStatus());

			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			if (null != p.getPlanStartDate()) {
				dataRow.createCell(4).setCellValue(p.getPlanStartDate().format(dateFormatter));
			} else {
				dataRow.createCell(4).setCellValue("N/A");
			}

			if (null != p.getPlanEndDate()) {
				dataRow.createCell(5).setCellValue(p.getPlanEndDate().format(dateFormatter));
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}

			if (null != p.getBenefitAmt()) {
				dataRow.createCell(6).setCellValue(p.getBenefitAmt());
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}

			dataRowIndex++;
		}

		ServletOutputStream so = response.getOutputStream();
		wb.write(so);
		wb.close();

		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		Paragraph p = new Paragraph("Citizen Plan Info", fontTiltle);

		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);

		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setSpacingBefore(6);

		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Plan Start Date");
		table.addCell("Plan End Date");

		List<CitizenPlan> plans = repo.findAll();

		for (CitizenPlan plan : plans) {

			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate() + "");
			table.addCell(plan.getPlanEndDate() + "");
		}

		document.add(table);

		document.close();
		return true;
	}

}
