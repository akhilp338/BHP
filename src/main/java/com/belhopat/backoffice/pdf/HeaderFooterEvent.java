package com.belhopat.backoffice.pdf;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Value;

import com.belhopat.backoffice.util.servlet.BelhopatServletContextInfo;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterEvent extends PdfPageEventHelper {
	
	@Value("#{pdfConfiguration['pdf.resources.root']}")
	private String pdfResourceRootPath;

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
		PdfPTable headerTable = new PdfPTable(1);
		headerTable.setTotalWidth(PageSize.A4.getWidth());
		PdfPCell headerCell = new PdfPCell();
		headerCell.setPadding(100f);
		headerCell.setPaddingBottom(30f);
		headerCell.setPaddingTop(30f);
		headerCell.setVerticalAlignment(Rectangle.ALIGN_CENTER);
		headerCell.setBorder(Rectangle.NO_BORDER);
		headerCell.setFixedHeight(100);
		String logoPath = pdfResourceRootPath + PDFConstants.LOGO_JPG;
		Image logoImage = Image.getInstance(logoPath);
		headerCell.addElement(logoImage);
		headerTable.addCell(headerCell);
		return headerTable;
	}

	protected PdfPTable getFooter() throws BadElementException, MalformedURLException, IOException {
		PdfPTable footerTable = new PdfPTable(1);
		footerTable.setTotalWidth(PageSize.A4.getWidth());
		footerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		String footerPath = pdfResourceRootPath + PDFConstants.FOOTER_JPG;
		Image footerImage = Image.getInstance(footerPath);
		footerTable.addCell(footerImage);
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
