package com.sb.majorproject.utils;

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
import com.sb.majorproject.entity.ApplicationDetails;
import com.sb.majorproject.entity.CorrespondanceNotices;


import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {
	// itext-pdf/open-pdf/ aspose
	public void generate(HttpServletResponse response, List<CorrespondanceNotices> records, File f) throws Exception {
		Document document = new Document(PageSize.A3);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		// creating font
		// setting font style and size
		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD);
		fontTitle.setSize(20);
		Paragraph p = new Paragraph("CitizenPlans", fontTitle);
		p.setAlignment(p.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(7);
		table.setSpacingBefore(5);
		table.addCell("caseNo");
		table.addCell("PlanName");
		table.addCell("PlanStatus");
		table.addCell("BenifitAmt");
		table.addCell("DenialReason");
		table.addCell("StartDate");
		table.addCell("EndDate");
//		table.addCell("");

		for (CorrespondanceNotices notice : records) {
			table.addCell(String.valueOf(notice.getCaseNo()));
			table.addCell(notice.getPlanName());
			table.addCell(notice.getPlanStatus());
			table.addCell(notice.getBenifitAmt());
			table.addCell(notice.getDenialReason());
			if (null != notice.getEligStartDate()) {
				table.addCell(notice.getEligStartDate() + "");
			} else {
				table.addCell("N/A");
			}
			if (null != notice.getEligEndDate()) {
				table.addCell(notice.getEligEndDate() + "");
			} else {
				table.addCell("N/A");
			}
//			if (null != notice.getPlanStartDate()) {
//				table.addCell(notice.getBenfitAmt() + "");
//			} else {
//				table.addCell("N/A");
//			}
		}
		document.add(table);
		document.close();

		
	}
}
