package com.belhopat.backoffice.pdf;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.util.servlet.BelhopatServletContextInfo;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

@Component
public class BasePDFGenerator {

	@Value("#{pdfConfiguration['pdf.resources.root']}")
	private String pdfResourceRootPath;

	public Font italicGray10Font = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.GRAY);
	public Font boldItalicGray10Font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLDITALIC, BaseColor.GRAY);
	public Font bold10Font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
	public Font underlined10Font = new Font(Font.FontFamily.HELVETICA, 10, Font.UNDERLINE);
	public Font normal10Font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

	public Font bold12Font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	public Font zapfdingbats = new Font(Font.FontFamily.ZAPFDINGBATS, 8);

	public byte[] getPDFContents()
			throws MalformedURLException, IOException, DocumentException, ParseException {
		return null;
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
		headingChunk.setFont(normal10Font);
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

	protected PdfPCell getCellContent(String content) {
		Phrase valuePhrase = new Phrase(content);
		valuePhrase.setFont(normal10Font);
		PdfPCell valueCell = new PdfPCell(valuePhrase);
		valueCell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
		valueCell.setFixedHeight(20f);
		return valueCell;
	}

	protected PdfPCell getCellContent(String content, int horizontalAlignment) {
		Phrase contentPhrase = new Phrase(content);
		contentPhrase.setFont(normal10Font);
		PdfPCell contentCell = new PdfPCell(contentPhrase);
		contentCell.setHorizontalAlignment(horizontalAlignment);
		contentCell.setFixedHeight(20f);
		return contentCell;
	}
	
	protected PdfPCell getCellContent(String content, int horizontalAlignment, Font font) {
		Phrase contentPhrase = new Phrase(content);
		contentPhrase.setFont(font);
		PdfPCell contentCell = new PdfPCell(contentPhrase);
		contentCell.setHorizontalAlignment(horizontalAlignment);
		contentCell.setFixedHeight(20f);
		return contentCell;
	}

	protected PdfPCell getCellContent(String content, int horizontalAlignment, BaseColor backgroundColor) {
		Phrase contentPhrase = new Phrase(content);
		contentPhrase.setFont(normal10Font);
		PdfPCell contentCell = new PdfPCell(contentPhrase);
		contentCell.setHorizontalAlignment(horizontalAlignment);
		contentCell.setBackgroundColor(backgroundColor);
		contentCell.setFixedHeight(20f);
		return contentCell;
	}
	
	protected PdfPCell getCellContent(String content, int horizontalAlignment,Font font, BaseColor backgroundColor) {
		Phrase contentPhrase = new Phrase(content);
		contentPhrase.setFont(font);
		PdfPCell contentCell = new PdfPCell(contentPhrase);
		contentCell.setHorizontalAlignment(horizontalAlignment);
		contentCell.setBackgroundColor(backgroundColor);
		return contentCell;
	}

	protected Anchor getAnchorElement(String text, String referenceUrl) {
		Chunk chunk = new Chunk("here", normal10Font);
		Anchor anchor = new Anchor(chunk);
		anchor.setReference(referenceUrl);
		return anchor;
	}

	protected Chunk getBelhopatWebLink() {
		Chunk websiteRefChunk = new Chunk("www.belhopat.com", italicGray10Font);
		Anchor anchor = new Anchor(websiteRefChunk);
		anchor.setReference("www.belhopat.com");
		return websiteRefChunk;
	}

	protected String getCatalinaBase() {
		String catalinaHome = System.getProperty("catalina.base");
		return catalinaHome;
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
}
