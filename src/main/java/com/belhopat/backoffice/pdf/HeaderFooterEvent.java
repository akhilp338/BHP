package com.belhopat.backoffice.pdf;

import java.io.IOException;
import java.net.MalformedURLException;

import com.belhopat.backoffice.util.servlet.BelhopatServletContextInfo;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterEvent extends PdfPageEventHelper {
	
	Font boldFont10 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	Font boldBlueFont10 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLUE);
	Font normalFont10 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
	
	protected PdfPTable header;

	protected PdfPTable footer;

	public HeaderFooterEvent() throws BadElementException, MalformedURLException, IOException {
		header = getHeader();
		footer = getFooter();
	}

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		header.writeSelectedRows(0, -1, 0, PageSize.A4.getHeight(), writer.getDirectContent());
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		footer.writeSelectedRows(0, -1, 0, footer.getTotalHeight(), writer.getDirectContent());
	}

	protected PdfPTable getHeader() throws BadElementException, MalformedURLException, IOException {
		PdfPTable headerTable = new PdfPTable(2);
		headerTable.setTotalWidth(PageSize.A4.getWidth());
		headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell headerCell = new PdfPCell();
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setFixedHeight(100);	
		String logoPath = getContextPath() + PDFConstants.PDF_RES_PATH + PDFConstants.LOGO_JPG;
		Image logoImage = Image.getInstance(logoPath);
		headerCell.addElement(logoImage);
		headerTable.addCell("");
		headerTable.addCell(headerCell);
		return headerTable;
	}

	protected PdfPTable getFooter() throws BadElementException, MalformedURLException, IOException {
		PdfPTable footerTable = new PdfPTable(1);
		footerTable.setTotalWidth(PageSize.A4.getWidth());
		footerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		PdfPCell footerCell = new PdfPCell();
		footerCell.setBorder(Rectangle.NO_BORDER);
		footerCell.setPadding(30f);
		String company = "Belhopat Global Services Pvt Ltd, 2nd Floor, ";
		Phrase companyPhrase = new Phrase(company,boldFont10);
		String address = "#2580, Spandana Building , 27th Main Road, 13th Cross, HSR Layout – 1st Sector, "
				+ "Bangalore, Karnataka 560102,India (Behind Pizza Hut). ";
		Phrase addressPhrase = new Phrase(address,normalFont10);
		String phone = "Phone: +91 99005 62575, +91 8049570935";
		Phrase phonePhrase = new Phrase(phone,boldFont10);
		String website =  "website: ";
		Phrase websitePhrase = new Phrase(website,boldFont10);
		String websiteRef =  "www.belhopat.com";
		Phrase websiteRefPhrase = new Phrase(websiteRef,boldBlueFont10);
		Anchor anchor = new Anchor(websiteRefPhrase);
		anchor.setReference(websiteRef);
		Phrase footerPhrase = new Phrase();
		footerPhrase.add(companyPhrase);
		footerPhrase.add(addressPhrase);
		footerPhrase.add(phonePhrase);
		footerPhrase.add(websitePhrase);
		footerPhrase.add(websiteRefPhrase);
		footerCell.addElement(footerPhrase);
		footerTable.addCell(footerCell);
		return footerTable;
	}

	protected String getContextPath() {
		String realPath = "";
		if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
			realPath = BelhopatServletContextInfo.getRealPath();
		} else if (System.getProperty("os.name").equalsIgnoreCase("Windows")) {
			String contextPath = BelhopatServletContextInfo.getContextPath();
			realPath = getCatalinaBase().concat("/webapps").concat(contextPath);
		} else {
			String contextPath = BelhopatServletContextInfo.getContextPath();
			realPath = getCatalinaBase().concat("/webapps").concat(contextPath);
		}
		return realPath;
	}

	protected String getCatalinaBase() {
		String catalinaHome = System.getProperty("catalina.base");
		return catalinaHome;
	}
}
