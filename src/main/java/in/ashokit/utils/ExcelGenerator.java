package in.ashokit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import in.ashokit.entity.CitizenPlan;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {

	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception {
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

		FileOutputStream fos = new FileOutputStream(new File("Plans.xls"));
		wb.write(fos);
		fos.close();
		
		ServletOutputStream so = response.getOutputStream();
		wb.write(so);
		wb.close();
	}
}
