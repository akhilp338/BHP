package com.belhopat.backoffice.pdf;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

@Component
public class BasePDFGenerator {

	@Value("#{pdfConfiguration['pdf.resources.root']}")
	private String pdfResourceRootPath;
	
	public Font HEADING_FONT = FontFactory.getFont(FontFactory.HELVETICA, 15f);
	public Font SUB_HEADING_FONT = FontFactory.getFont(FontFactory.HELVETICA, 12f);
	public Font TEXT_FONT = FontFactory.getFont(FontFactory.HELVETICA, 14f);
	public Font TEXT_FONT_BLUE = FontFactory.getFont(FontFactory.HELVETICA, 12f);
	public Font TEXT_FONT_BOLD = FontFactory.getFont(FontFactory.HELVETICA, 12f);

	public void getPDFContents(Document document)
			throws MalformedURLException, IOException, DocumentException, ParseException {
		document.add(new PdfPTable(1));
	}

	protected PdfPCell getDefaultContentCell() {
		PdfPCell contentCell = new PdfPCell();
		contentCell.setBorder(Rectangle.NO_BORDER);
		contentCell.setPaddingLeft(20f);
		contentCell.setPaddingRight(20f);
		contentCell.setFixedHeight(PageSize.A4.getHeight() - 180);
		return contentCell;
	}

	protected PdfPCell getHeadingCell(String heading) {
		Chunk headingChunk = new Chunk(heading);
		headingChunk.setFont(HEADING_FONT);
		Phrase headingPhrase = new Phrase(headingChunk);
		PdfPCell headingCell = new PdfPCell(headingPhrase);
		headingCell.setBorder(Rectangle.NO_BORDER);
		headingCell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
		headingCell.setVerticalAlignment(Rectangle.ALIGN_BOTTOM);
		headingCell.setPaddingBottom(30f);
		return headingCell;
	}

	protected PdfPTable getClosingScript() {
		PdfPTable closingScriptTable = new PdfPTable(1);
		String closingScript = "Thank you,";
		Phrase closingScriptPhrase = new Phrase();
		closingScriptPhrase.add(Chunk.NEWLINE);
		closingScriptPhrase.add(closingScript);
		closingScriptPhrase.add(Chunk.NEWLINE);
		closingScriptPhrase.add(Chunk.NEWLINE);
		PdfPCell closingScriptCell = new PdfPCell(closingScriptPhrase);
		closingScriptCell.setBorder(Rectangle.NO_BORDER);
		closingScriptCell.setHorizontalAlignment(Rectangle.ALIGN_JUSTIFIED);
		closingScriptCell.setVerticalAlignment(Rectangle.ALIGN_BOTTOM);
		closingScriptCell.setPaddingTop(10f);
		closingScriptCell.setPaddingBottom(10f);
		closingScriptTable.addCell(closingScriptCell);
		return closingScriptTable;
	}

	protected PdfPCell getDetailNameCell(String detailName) {
		Phrase detailNamePhrase = new Phrase(detailName);
		detailNamePhrase.setFont(TEXT_FONT_BOLD);
		PdfPCell detailNameCell = new PdfPCell(detailNamePhrase);
		detailNameCell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
		detailNameCell.setFixedHeight(20f);
		return detailNameCell;
	}

	protected PdfPCell getDetailValueCell(String detailValue) {
		Phrase detailValuePhrase = new Phrase(detailValue);
		PdfPCell detailValueCell = new PdfPCell(detailValuePhrase);
		detailValueCell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
		return detailValueCell;
	}

	protected Anchor getAnchorElement(String text, String referenceUrl) {
		Chunk chunk = new Chunk("here", TEXT_FONT_BLUE);
		Anchor anchor = new Anchor(chunk);
		anchor.setReference(referenceUrl);
		return anchor;
	}

	protected String getCatalinaBase() {
		String catalinaHome = System.getProperty("catalina.base");
		return catalinaHome;
	}
}
