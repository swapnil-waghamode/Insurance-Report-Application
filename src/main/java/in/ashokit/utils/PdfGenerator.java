package in.ashokit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.entity.CitizenPlan;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {
	
	public void generate(HttpServletResponse response, List<CitizenPlan> plans , File f ) throws Exception {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		PdfWriter.getInstance(document, new FileOutputStream(f));
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
	}

}
