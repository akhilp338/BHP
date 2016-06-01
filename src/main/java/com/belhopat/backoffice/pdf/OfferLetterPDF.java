package com.belhopat.backoffice.pdf;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;

import com.belhopat.backoffice.util.DateUtil;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;

public class OfferLetterPDF extends BasePDFGenerator {
	public void getPDFContents(Document document)
			throws MalformedURLException, IOException, DocumentException, ParseException {
		document.add(getHeading());
		document.add(getHeadingContent());
		document.add(getAcceptanceAndCommencementContent());
		document.add(getConfidentialityContent());
		document.add(getCompensationContent()); 
		document.add(getProbationContent()); 
		document.add(getWorkingHoursContent());
		}

	private PdfPTable getAcceptanceAndCommencementContent() {
		PdfPTable acceptanceAndCommencementContent = new PdfPTable(1);
		acceptanceAndCommencementContent.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		acceptanceAndCommencementContent.setWidthPercentage(100f);
		acceptanceAndCommencementContent.addCell(getParagraphHeading("Acceptance and Commencement"));
		acceptanceAndCommencementContent.addCell(getAcceptanceAndCommencementParagraph());
		return acceptanceAndCommencementContent;
	}
	
	private PdfPTable getConfidentialityContent() {
		PdfPTable confidentialityContent = new PdfPTable(1);
		confidentialityContent.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		confidentialityContent.setWidthPercentage(100f);
		confidentialityContent.addCell(getParagraphHeading("Confidentiality"));
		confidentialityContent.addCell(getConfidentialityParagraph());
		return confidentialityContent;
	}
	
	private PdfPTable getCompensationContent() {
		PdfPTable compensationContent = new PdfPTable(1);
		compensationContent.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		compensationContent.setWidthPercentage(100f);
		compensationContent.addCell(getParagraphHeading("Compensation"));
		compensationContent.addCell(getCompensationParagraph());
		return compensationContent;
	}
	
	private PdfPTable getProbationContent() {
		PdfPTable probationContent = new PdfPTable(1);
		probationContent.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		probationContent.setWidthPercentage(100f);
		probationContent.addCell(getParagraphHeading("Probation"));
		probationContent.addCell(getProbationParagraph());
		return probationContent;
	}

	private PdfPTable getWorkingHoursContent() {
		PdfPTable workingHoursContent = new PdfPTable(1);
		workingHoursContent.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		workingHoursContent.setWidthPercentage(100f);
		workingHoursContent.addCell(getParagraphHeading("Working Hours"));
		workingHoursContent.addCell(getWorkingHoursParagraph());
		return workingHoursContent;
	}

	private Paragraph getAcceptanceAndCommencementParagraph() {
		Paragraph aAndCPara = new Paragraph();
		Chunk part1 = new Chunk("Your appointment will be effective from ", normal10Font);
		Chunk appoinmentDate = new Chunk("March 07, 2016", bold10Font);
		Chunk part2 = new Chunk(". Please contact us immediately if you require an alternative joiningdate. "
				+ "If you do not confirm your acceptance or if we are unable to set an alternative date, "
				+ "this offer will be withdrawn. This offer is valid only if you acknowledge and confirm that you’re joining, "
				+ "through email within 2 days.", normal10Font);
		aAndCPara.add(part1);
		aAndCPara.add(appoinmentDate);
		aAndCPara.add(part2);
		return aAndCPara;
	}
	
	private Paragraph getConfidentialityParagraph() {
		Paragraph confidentialityPara = new Paragraph();
		Chunk content = new Chunk("Sharing the details of your offer with others would imply a breach of confidentiality"
				+ " and could invite suitable disciplinary action. This is a very private and confidential document. "
				+ "Please maintain the confidentiality and ensure that the details of your offer are not shared"
				+ " with anyone outside the Human Resource Team of Belhopat.", normal10Font);
		confidentialityPara.add(content);
		return confidentialityPara;
	}
	
	private Paragraph getCompensationParagraph() {
		Paragraph compensationPara = new Paragraph();
		Chunk part1 = new Chunk("Your Annual Gross Cost To The Company, hereafter referred to as Annual Gross CTC will be ", normal10Font);
		Chunk salaryAmount = new Chunk("INR 6,50,000/- (Six Lakhs and Fifty Thousand Indian Rupees Only).", bold10Font);
		Chunk part2 = new Chunk(" The deductions as per the company policies and the government norms would be applicable . "
				+ "Your compensation and benefits may be amended at the sole discretion of the company.", normal10Font);
		compensationPara.add(part1);
		compensationPara.add(salaryAmount);
		compensationPara.add(part2);
		return compensationPara;
	}
	
	private Paragraph getProbationParagraph() {
		Paragraph probationPara = new Paragraph();
		Chunk content = new Chunk("The first 6 months from your date of joining would be "
				+ "considered as the probationary period.", normal10Font);
		probationPara.add(content);
		return probationPara;
	}
	
	private Paragraph getWorkingHoursParagraph() {
		Paragraph WorkingHoursPara = new Paragraph();
		Chunk content = new Chunk("The general work timing at Belhopat is from 9 AM to 6:30 PM. "
				+ "However, you are required to abide by the timings of your Work Location / Business Unit. "
				+ "We presently have a five-day (Monday to Friday) work week.", normal10Font);
		WorkingHoursPara.add(content);
		return WorkingHoursPara;
	}

	private Phrase getParagraphHeading(String headingText) {
		Phrase heading = new Phrase();
		Chunk headingChunk = new Chunk(headingText, bold10Font);
		heading.add(Chunk.NEWLINE);
		heading.add(headingChunk);
		heading.add(Chunk.NEWLINE);
		heading.add(Chunk.NEWLINE);
		return heading;
	}

	private PdfPTable getHeading() throws ParseException {
		PdfPTable heading = new PdfPTable(new float[] { 2, 1 });
		heading.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		heading.setWidthPercentage(100f);
		heading.addCell(new Phrase(DateUtil.toMMMMddYYYY(new Date()), bold10Font));
		heading.addCell(getCompanyNameAndCode());
		heading.addCell(getEmployeeAddress());
		heading.addCell(getCompanyAddress());
		return heading;
	}

	private PdfPTable getHeadingContent() throws ParseException {
		PdfPTable headingPara = new PdfPTable(1);
		headingPara.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		headingPara.setWidthPercentage(100f);
		headingPara.addCell(getRefernceNumberCell());
		headingPara.addCell(getHeadingParagraph());
		return headingPara;
	}

	private Paragraph getHeadingParagraph() {
		Paragraph headingPara = new Paragraph();
		Chunk dear = new Chunk("Dear ", normal10Font);
		Chunk employeeName = new Chunk("Mr. Akhil Prakash,", bold10Font);
		Chunk firstPara = new Chunk("At Belhopat, we intent to revolutionize the world and benefit humanity "
				+ "with continuous inventions and innovations of our products and services. "
				+ "We are starting our journey by providing excellent quality "
				+ "Information Technology services to our esteemed customers.", normal10Font);
		Chunk secondPara1 = new Chunk("We are pleased to confirm our offer of employment to you as ", normal10Font);
		Chunk designation = new Chunk("Senior Software Engineer", bold10Font);
		Chunk secondPara2 = new Chunk(
				" with Belhopat Global services Pvt.Ltd. (The Company), as per the details given below.", normal10Font);
		headingPara.add(dear);
		headingPara.add(employeeName);
		headingPara.add(Chunk.NEWLINE);
		headingPara.add(Chunk.NEWLINE);
		headingPara.add(firstPara);
		headingPara.add(Chunk.NEWLINE);
		headingPara.add(Chunk.NEWLINE);
		headingPara.add(secondPara1);
		headingPara.add(designation);
		headingPara.add(secondPara2);

		return headingPara;
	}

	private Phrase getRefernceNumberCell() {
		Phrase referenceNumberPhrase = new Phrase();
		Chunk referenceNumberLabel = new Chunk("Your Belhopat Reference Number: ", bold10Font);
		Chunk referenceNumber = new Chunk("CSOL-1602-01", bold10Font);
		referenceNumberPhrase.add(Chunk.NEWLINE);
		referenceNumberPhrase.add(referenceNumberLabel);
		referenceNumberPhrase.add(referenceNumber);
		referenceNumberPhrase.add(Chunk.NEWLINE);
		referenceNumberPhrase.add(Chunk.NEWLINE);
		return referenceNumberPhrase;
	}

	private Phrase getCompanyAddress() {
		Phrase addressPhrase = new Phrase();
		Chunk address = new Chunk("#2580 Spandana Building, 2nd Floor,", italicGray10Font);
		Chunk address1 = new Chunk("27th Main, 13th Cross, 1st Sector", italicGray10Font);
		Chunk address2 = new Chunk("HSR Layout, Bangalore - 560102", italicGray10Font);
		Chunk address3 = new Chunk("Karnataka, India", italicGray10Font);
		Chunk telNo = new Chunk("Tel: +91 80495 70935", italicGray10Font);
		Chunk cellNo = new Chunk("Cell: +91 99005 62575", italicGray10Font);
		Chunk website = getBelhopatWebLink();
		addressPhrase.add(address);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(address1);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(address2);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(address3);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(telNo);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(cellNo);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(website);
		return addressPhrase;
	}

	private Phrase getEmployeeAddress() {
		Phrase addressPhrase = new Phrase();
		Chunk address = new Chunk("Mr. Akhil Prakash", bold10Font);
		Chunk address1 = new Chunk("Prakasam,", normal10Font);
		Chunk address2 = new Chunk("Palamconam,", normal10Font);
		Chunk street = new Chunk("Perumkulam P O ,", normal10Font);
		Chunk city = new Chunk("Attingal,", normal10Font);
		Chunk state = new Chunk("Kerala,", normal10Font);
		Chunk zipCode = new Chunk("-695102", normal10Font);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(address);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(address1);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(address2);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(street);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(city);
		addressPhrase.add(Chunk.NEWLINE);
		addressPhrase.add(state);
		addressPhrase.add(zipCode);
		return addressPhrase;
	}

	private Phrase getCompanyNameAndCode() {
		Phrase companyPhrase = new Phrase();
		Chunk company = new Chunk("Belhopat Global Services Pvt. Ltd.", boldItalicGray10Font);
		Chunk companyCode = new Chunk("(CIN - U72900KA2014PTC074624)", italicGray10Font);
		companyPhrase.add(company);
		companyPhrase.add(Chunk.NEWLINE);
		companyPhrase.add(companyCode);
		companyPhrase.add(Chunk.NEWLINE);
		companyPhrase.add(Chunk.NEWLINE);
		return companyPhrase;
	}
}
