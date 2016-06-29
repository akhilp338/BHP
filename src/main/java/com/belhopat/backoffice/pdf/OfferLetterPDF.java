package com.belhopat.backoffice.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;

import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.EmployeeSalary;
import com.belhopat.backoffice.util.DateUtil;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class OfferLetterPDF extends BasePDFGenerator {

	public byte[] getPDFContents(Employee employee)
			throws MalformedURLException, IOException, DocumentException, ParseException {

		String fileName = "OfferLetter.pdf";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		OutputStream outputStream = new FileOutputStream("/home/sujith/Desktop/" + fileName);
		Document document = new Document(PageSize.A4, 50f, 50f, 150f, 60f);
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		writer.setBoxSize("art", PageSize.A4);
		HeaderFooterEvent event = new HeaderFooterEvent();
		writer.setPageEvent(event);
		setSampleEmployee(employee);
		addContentToDocument(document, employee);
		// PdfCopy pdfCopy = new PdfSmartCopy(document, outputStream);
		// String annexureAPath = getContextPath() +
		// PDFConstants.PDF_RES_PATH +
		// PDFConstants.ANEX_A;
		// InputStream anexA = new FileInputStream(annexureAPath);
		// PdfReader annexureAReader = new PdfReader(anexA);
		// int pages = annexureAReader.getNumberOfPages();
		// PdfImportedPage annexureAPage =
		// writer.getImportedPage(annexureAReader, pages);
		// String annexureBPath = getContextPath() +
		// PDFConstants.PDF_RES_PATH +
		// PDFConstants.ANEX_B;
		// InputStream anexB = new FileInputStream(annexureBPath);
		// PdfReader annexureBReader = new PdfReader(anexB);
		// PdfImportedPage annexureBPage =
		// writer.getImportedPage(annexureBReader, pages);
		// pdfCopy.addPage(annexureAPage);
		// pdfCopy.addPage(annexureBPage);
		// annexureAReader.close();
		// annexureBReader.close();
		document.close();
		outputStream.close();
		return outputStream.toByteArray();
//		return null;
	}

	private void setSampleEmployee(Employee employee) {
		employee.getEmployeeMaster().setFirstName("Akhil");
		employee.getEmployeeMaster().setFirstName(" ");
		employee.getEmployeeMaster().setLastName("Prakash");
	}

	public void testPdf() throws DocumentException, IOException {

		String annexureBPath = getContextPath() + PDFConstants.PDF_RES_PATH + PDFConstants.ANEX_B;
		String annexureAPath = getContextPath() + PDFConstants.PDF_RES_PATH + PDFConstants.ANEX_A;
		String[] files = { annexureAPath, annexureBPath };
		// step 1
		Document document = new Document();
		// step 2
		PdfCopy copy = new PdfCopy(document, new FileOutputStream("/home/sujith/Desktop/a.pdf"));
		// step 3
		document.open();
		// step 4
		PdfReader reader;
		int n;
		// loop over the documents you want to concatenate
		for (int i = 0; i < files.length; i++) {
			reader = new PdfReader(files[i]);
			// loop over the pages in that document
			n = reader.getNumberOfPages();
			for (int page = 0; page < n;) {
				copy.addPage(copy.getImportedPage(reader, ++page));
			}
			copy.freeReader(reader);
		}
		// step 5
		document.close();
	}

	private void addContentToDocument(Document document, Employee employee)
			throws DocumentException, ParseException, MalformedURLException, IOException {
		document.open();
		document.add(getHeading(employee));
		document.add(getHeadingContent(employee));
		document.add(getAcceptanceAndCommencementContent(employee));
		document.add(getConfidentialityContent());
		document.add(getCompensationContent());
		document.add(getProbationContent());
		document.add(getWorkingHoursContent());
		document.newPage();
		document.add(getCodeOfConductContent());
		document.add(getEndingContent(employee));
		document.add(getAcknowledgementContent());
		document.newPage();
		document.add(getCompensationStructureTable(employee));
	}

	private PdfPTable getAcceptanceAndCommencementContent(Employee employee) throws ParseException {
		PdfPTable acceptanceAndCommencementContent = new PdfPTable(1);
		acceptanceAndCommencementContent.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		acceptanceAndCommencementContent.setWidthPercentage(100f);
		acceptanceAndCommencementContent.addCell(getParagraphHeading("Acceptance and Commencement"));
		acceptanceAndCommencementContent.addCell(getAcceptanceAndCommencementParagraph(employee));
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

	private PdfPTable getCodeOfConductContent() {
		PdfPTable codeOfConductContent = new PdfPTable(1);
		codeOfConductContent.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		codeOfConductContent.setWidthPercentage(100f);
		codeOfConductContent.addCell(getParagraphHeading("Code of Conduct"));
		codeOfConductContent.addCell(getCodeOfConductParagraph());
		return codeOfConductContent;
	}

	private PdfPTable getEndingContent(Employee employee)
			throws BadElementException, MalformedURLException, IOException {
		PdfPTable endContent = new PdfPTable(1);
		endContent.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		endContent.setWidthPercentage(100f);
		endContent.addCell(getEndingParagraph());
		endContent.addCell(getSignAndSeal());
		endContent.addCell(getHRDetails(employee));
		return endContent;
	}

	private PdfPTable getAcknowledgementContent() {
		PdfPTable acceptanceAndCommencementContent = new PdfPTable(1);
		acceptanceAndCommencementContent.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		acceptanceAndCommencementContent.setWidthPercentage(100f);
		acceptanceAndCommencementContent.addCell(getAcknoledgementHeading());
		acceptanceAndCommencementContent.addCell(getAcknoledgementParagraph());
		return acceptanceAndCommencementContent;
	}

	private Paragraph getAcceptanceAndCommencementParagraph(Employee employee) throws ParseException {
		Paragraph aAndCPara = new Paragraph();
		Chunk part1 = new Chunk("Your appointment will be effective from ", normal10Font);
		Chunk appoinmentDate = new Chunk(
				employee.getJoiningDate() == null ? "" : DateUtil.toDdMmYyyy(employee.getJoiningDate()), bold10Font);
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
		Chunk content = new Chunk(
				"Sharing the details of your offer with others would imply a breach of confidentiality"
						+ " and could invite suitable disciplinary action. This is a very private and confidential document. "
						+ "Please maintain the confidentiality and ensure that the details of your offer are not shared"
						+ " with anyone outside the Human Resource Team of Belhopat.",
				normal10Font);
		confidentialityPara.add(content);
		return confidentialityPara;
	}

	private Paragraph getCompensationParagraph() {
		Paragraph compensationPara = new Paragraph();
		Chunk part1 = new Chunk(
				"Your Annual Gross Cost To The Company, hereafter referred to as Annual Gross CTC will be ",
				normal10Font);
		Chunk salaryAmount = new Chunk("INR 6,50,000/- (Six Lakhs and Fifty Thousand Indian Rupees Only).", bold10Font);
		Chunk part2 = new Chunk(
				" The deductions as per the company policies and the government norms would be applicable . "
						+ "Your compensation and benefits may be amended at the sole discretion of the company.",
				normal10Font);
		compensationPara.add(part1);
		compensationPara.add(salaryAmount);
		compensationPara.add(part2);
		return compensationPara;
	}

	private Paragraph getProbationParagraph() {
		Paragraph probationPara = new Paragraph();
		Chunk content = new Chunk(
				"The first 6 months from your date of joining would be " + "considered as the probationary period.",
				normal10Font);
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

	private PdfPTable getCodeOfConductParagraph() {
		PdfPTable codeOfCunductPara = new PdfPTable(new float[] { 1, 12 });
		codeOfCunductPara.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		String[] listItems = {
				"You shall be governed by the Company’s rules and regulations that may be promulgated from time to time.",
				"You shall take up any assignment that may be offered to you by the company.",
				"You shall be expected to abide by the rules and regulations of the company, be courteous, "
						+ "honest and professional within the company or with its clients/customers, "
						+ "and maintain and represent the Company’s high standards of professional Services at all times, "
						+ "whether in the Company or at its client’s site(s).",
				"You shall be responsible for all company properties and material that are in your possession, like telephones, "
						+ "computers, and projectors etc. that have been provided to you.",
				"You shall not publicly criticize, defame or misrepresent the Company and shall not, knowingly or unknowingly "
						+ "commit any such actions which may result in the Company’s image or business being adversely affected.",
				"During the course of your employment with the Company and 2 years after that, "
						+ "you will not solicit business of any nature, either directly or indirectly, "
						+ "for yourself, or for any other party, from the Company’s clients and / or customers." };
		Phrase bullet = new Phrase(String.valueOf((char) 108), zapfdingbats);
		for (String item : listItems) {
			codeOfCunductPara.addCell(bullet);
			codeOfCunductPara.addCell(new Phrase(item + " ", normal10Font));
		}
		return codeOfCunductPara;
	}

	private Paragraph getEndingParagraph() {
		Paragraph endingPara = new Paragraph();
		Chunk part1 = new Chunk(
				"We hope your association with us will be mutually beneficial, pleasant and fulfilling. "
						+ "Please return the duplicate copy of this letter, duly signed, in token of your acceptance.",
				normal10Font);
		Chunk regards = new Chunk("Warm Regards,", normal10Font);
		Chunk companyName = new Chunk("For Belhopat Global Services Pvt. Ltd.", bold10Font);
		endingPara.add(part1);
		endingPara.add(Chunk.NEWLINE);
		endingPara.add(Chunk.NEWLINE);
		endingPara.add(Chunk.NEWLINE);
		endingPara.add(regards);
		endingPara.add(Chunk.NEWLINE);
		endingPara.add(Chunk.NEWLINE);
		endingPara.add(companyName);
		return endingPara;
	}

	protected PdfPTable getSignAndSeal() throws BadElementException, MalformedURLException, IOException {
		PdfPTable signAndSealTable = new PdfPTable(4);
		signAndSealTable.setTotalWidth(PageSize.A4.getWidth());
		signAndSealTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		signAndSealTable.getDefaultCell().setFixedHeight(80);
		String signPath = getContextPath() + PDFConstants.PDF_RES_PATH + PDFConstants.HR_SIGN;
		Image signImage = Image.getInstance(signPath);
		String sealPath = getContextPath() + PDFConstants.PDF_RES_PATH + PDFConstants.BHP_SEAL;
		Image sealImage = Image.getInstance(sealPath);
		signAndSealTable.addCell(signImage);
		signAndSealTable.addCell(sealImage);
		signAndSealTable.completeRow();
		return signAndSealTable;
	}

	private Phrase getHRDetails(Employee employee) {
		Phrase HRPhrase = new Phrase();
		String firstName = employee.getHrManager().getEmployeeMaster() == null ? ""
				: employee.getHrManager().getEmployeeMaster().getFirstName() == null ? ""
						: employee.getHrManager().getEmployeeMaster().getFirstName();
		String middleName = employee.getHrManager().getEmployeeMaster() == null ? ""
				: employee.getHrManager().getEmployeeMaster().getMiddleName() == null ? ""
						: employee.getHrManager().getEmployeeMaster().getMiddleName();
		String lastName = employee.getHrManager().getEmployeeMaster() == null ? ""
				: employee.getHrManager().getEmployeeMaster().getLastName() == null ? ""
						: employee.getHrManager().getEmployeeMaster().getLastName() + ",";
		String hrName = firstName + middleName + lastName;
		Chunk name = new Chunk(hrName, bold10Font);
		Chunk designation = new Chunk(employee.getHrManager().getEmployeeMaster().getDesignation().getDescription(),
				bold10Font);
		HRPhrase.add(Chunk.NEWLINE);
		HRPhrase.add(name);
		HRPhrase.add(Chunk.NEWLINE);
		HRPhrase.add(designation);
		HRPhrase.add(Chunk.NEWLINE);
		return HRPhrase;
	}

	private Paragraph getAcknoledgementParagraph() {
		Paragraph acknoledgementPara = new Paragraph();
		Chunk para = new Chunk("I have read and understood the above terms and conditions of "
				+ "employment and the implication thereof. I hereby accept the aforesaid terms and conditions "
				+ "and agree to abide by the same.", normal10Font);
		Chunk name = new Chunk("Name:", normal10Font);
		Chunk sign = new Chunk("Signature:", normal10Font);
		Chunk date = new Chunk("                                                                    " + "Date:",
				normal10Font);
		acknoledgementPara.add(para);
		acknoledgementPara.add(Chunk.NEWLINE);
		acknoledgementPara.add(Chunk.NEWLINE);
		acknoledgementPara.add(name);
		acknoledgementPara.add(Chunk.NEWLINE);
		acknoledgementPara.add(Chunk.NEWLINE);
		acknoledgementPara.add(sign);
		acknoledgementPara.add(date);
		return acknoledgementPara;
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

	private PdfPCell getAcknoledgementHeading() {
		Phrase heading = new Phrase();
		Chunk headingChunk = new Chunk("ACKNOWLEDGEMENT & ACCEPTANCE", underlined10Font);
		heading.add(Chunk.NEWLINE);
		heading.add(headingChunk);
		heading.add(Chunk.NEWLINE);
		heading.add(Chunk.NEWLINE);
		PdfPCell headingCell = new PdfPCell(heading);
		headingCell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
		headingCell.setBorder(Rectangle.NO_BORDER);
		return headingCell;
	}

	private PdfPTable getHeading(Employee employee) throws ParseException {
		PdfPTable heading = new PdfPTable(new float[] { 2, 1 });
		heading.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		heading.setWidthPercentage(100f);
		heading.addCell(new Phrase(DateUtil.toMMMMddYYYY(new Date()), bold10Font));
		heading.addCell(getCompanyNameAndCode());
		heading.addCell(getEmployeeAddress(employee));
		heading.addCell(getCompanyAddress());
		return heading;
	}

	private PdfPTable getHeadingContent(Employee employee) throws ParseException {
		PdfPTable headingPara = new PdfPTable(1);
		headingPara.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		headingPara.setWidthPercentage(100f);
		headingPara.addCell(getRefernceNumberCell(employee));
		headingPara.addCell(getHeadingParagraph(employee));
		return headingPara;
	}

	private Paragraph getHeadingParagraph(Employee employee) {
		Paragraph headingPara = new Paragraph();
		String firstName = employee.getEmployeeMaster() == null ? ""
				: employee.getEmployeeMaster().getFirstName() == null ? ""
						: employee.getEmployeeMaster().getFirstName();
		String middleName = employee.getEmployeeMaster() == null ? ""
				: employee.getEmployeeMaster().getMiddleName() == null ? ""
						: employee.getEmployeeMaster().getMiddleName();
		String lastName = employee.getEmployeeMaster() == null ? ""
				: employee.getEmployeeMaster().getLastName() == null ? ""
						: employee.getEmployeeMaster().getLastName() + ",";
		String name = firstName + middleName + lastName;
		Chunk dear = new Chunk("Dear ", normal10Font);
		Chunk employeeName = new Chunk(name + ",", bold10Font);
		Chunk firstPara = new Chunk("At Belhopat, we intent to revolutionize the world and benefit humanity "
				+ "with continuous inventions and innovations of our products and services. "
				+ "We are starting our journey by providing excellent quality "
				+ "Information Technology services to our esteemed customers.", normal10Font);
		Chunk secondPara1 = new Chunk("We are pleased to confirm our offer of employment to you as ", normal10Font);
		String designationText = employee.getEmployeeMaster().getDesignation().getDescription();
		Chunk designation = new Chunk(designationText, bold10Font);
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

	private Phrase getRefernceNumberCell(Employee employee) {
		Phrase referenceNumberPhrase = new Phrase();
		Chunk referenceNumberLabel = new Chunk("Your Belhopat Reference Number: ", normal10Font);
		Chunk referenceNumber = new Chunk(employee.getEmployeeId(), bold10Font);
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

	private Phrase getEmployeeAddress(Employee employee) {
		Phrase addressPhrase = new Phrase();
		String firstName = employee.getEmployeeMaster() == null ? ""
				: employee.getEmployeeMaster().getFirstName() == null ? ""
						: employee.getEmployeeMaster().getFirstName();
		String middleName = employee.getEmployeeMaster() == null ? ""
				: employee.getEmployeeMaster().getMiddleName() == null ? ""
						: employee.getEmployeeMaster().getMiddleName();
		String lastName = employee.getEmployeeMaster() == null ? ""
				: employee.getEmployeeMaster().getLastName() == null ? ""
						: employee.getEmployeeMaster().getLastName() + ",";
		String address1Text = employee.getEmployeeMaster().getPermanentAddress() == null ? ""
				: employee.getEmployeeMaster().getPermanentAddress().getAddress1() == null ? ""
						: employee.getEmployeeMaster().getPermanentAddress().getAddress1() + ",";

		String address2Text = employee.getEmployeeMaster().getPermanentAddress() == null ? ""
				: employee.getEmployeeMaster().getPermanentAddress().getAddress2() == null ? ""
						: employee.getEmployeeMaster().getPermanentAddress().getAddress2() + ",";
		String streetText = employee.getEmployeeMaster().getPermanentAddress() == null ? ""
				: employee.getEmployeeMaster().getPermanentAddress().getStreet() == null ? ""
						: employee.getEmployeeMaster().getPermanentAddress().getStreet() + ",";
		String cityText = employee.getEmployeeMaster().getPermanentAddress() == null ? ""
				: employee.getEmployeeMaster().getPermanentAddress().getCity().getDescription() == null ? ""
						: employee.getEmployeeMaster().getPermanentAddress().getCity().getDescription() + ",";
		String stateText = employee.getEmployeeMaster().getPermanentAddress() == null ? ""
				: employee.getEmployeeMaster().getPermanentAddress().getCity().getState().getDescription() == null ? ""
						: employee.getEmployeeMaster().getPermanentAddress().getCity().getState().getDescription()
								+ ",";
		String zipCodeText = employee.getEmployeeMaster().getPermanentAddress() == null ? ""
				: employee.getEmployeeMaster().getPermanentAddress().getZipCode() == null ? ""
						: "-" + employee.getEmployeeMaster().getPermanentAddress().getZipCode().toString();
		Chunk address = new Chunk(firstName + middleName + lastName, bold10Font);
		Chunk address1 = new Chunk(address1Text, normal10Font);
		Chunk address2 = new Chunk(address2Text, normal10Font);
		Chunk street = new Chunk(streetText, normal10Font);
		Chunk city = new Chunk(cityText, normal10Font);
		Chunk state = new Chunk(stateText, normal10Font);
		Chunk zipCode = new Chunk(zipCodeText, normal10Font);
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

	private PdfPTable getCompensationStructureTable(Employee employee) {
		PdfPTable compensationStructure = new PdfPTable(1);
		compensationStructure.setWidthPercentage(100f);
		compensationStructure.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		compensationStructure.addCell(getPageHeading("Compensation Structure"));
		compensationStructure.addCell(getNameAndDesignation(employee));
		compensationStructure.addCell(getCompensationStructureContent(employee));
		return compensationStructure;
	}

	private PdfPTable getCompensationStructureContent(Employee employee) {
		PdfPTable CSContent = new PdfPTable(new float[] { 2, 1, 1 });
		CSContent.setWidthPercentage(100f);
		EmployeeSalary salary = employee.getEmployeeMaster().getSalary();
		if (salary != null) {
			CSContent.addCell(getCellContent("Components", Rectangle.ALIGN_LEFT, bold10Font, BaseColor.GRAY));
			CSContent.addCell(getCellContent("Per Month (In INR)", Rectangle.ALIGN_CENTER, bold10Font, BaseColor.GRAY));
			CSContent.addCell(getCellContent("Per Annum (In INR)", Rectangle.ALIGN_CENTER, bold10Font, BaseColor.GRAY));
			CSContent.addCell(getCellContent("Basic Salary"));
			CSContent.addCell(getCellContent(salary.getBasicSalary().toString(), Rectangle.ALIGN_RIGHT));
			Double annualBasic = salary.getBasicSalary() * 12D;
			CSContent.addCell(getCellContent(annualBasic.toString(), Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("House Rent Allowance"));
			CSContent.addCell(getCellContent(salary.getHra().toString(), Rectangle.ALIGN_RIGHT));
			Double annualHRA = salary.getHra() * 12D;
			CSContent.addCell(getCellContent(annualHRA.toString(), Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("Medical Allowance"));
			CSContent.addCell(getCellContent(salary.getMedicalAllowance().toString(), Rectangle.ALIGN_RIGHT));
			Double annualMedicalAllowence = salary.getHra() * 12D;
			CSContent.addCell(getCellContent(annualMedicalAllowence.toString(), Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("Conveyance Allowance"));
			CSContent.addCell(getCellContent(salary.getConveyanceAllowance().toString(), Rectangle.ALIGN_RIGHT));
			Double annualConveyanceAllowance = salary.getConveyanceAllowance() * 12D;
			CSContent.addCell(getCellContent(annualConveyanceAllowance.toString(), Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("Flexi Benefits Kit"));
			CSContent.addCell(getCellContent(salary.getFlexyBenKit().toString(), Rectangle.ALIGN_RIGHT));
			Double annualFlexyBenKit = salary.getFlexyBenKit() * 12D;
			CSContent.addCell(getCellContent(annualFlexyBenKit.toString(), Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("Statutory Bonus"));
			CSContent.addCell(getCellContent("-", Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("-", Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent(""));
			CSContent.addCell(getCellContent(""));
			CSContent.addCell(getCellContent(""));
			CSContent.addCell(getCellContent("Gross Salary", Rectangle.ALIGN_LEFT, bold10Font, BaseColor.GRAY));
			CSContent.addCell(getCellContent(salary.getGrossSalary().toString(), Rectangle.ALIGN_RIGHT, bold10Font,
					BaseColor.GRAY));
			Double annualGrossSalary = salary.getGrossSalary() * 12D;
			CSContent.addCell(
					getCellContent(annualGrossSalary.toString(), Rectangle.ALIGN_RIGHT, bold10Font, BaseColor.GRAY));
			CSContent.addCell(getCellContent(""));
			CSContent.addCell(getCellContent(""));
			CSContent.addCell(getCellContent(""));
			CSContent.addCell(getCellContent("EPF Contribution by Belhopat"));
			CSContent.addCell(getCellContent(salary.getPfCompContrbtn().toString(), Rectangle.ALIGN_RIGHT));
			Double annualPfCompContrbtn = salary.getPfCompContrbtn() * 12D;
			CSContent.addCell(getCellContent(annualPfCompContrbtn.toString(), Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("ESI Contribution by Belhopat"));
			CSContent.addCell(getCellContent(salary.getEsiByEmplyr().toString(), Rectangle.ALIGN_RIGHT));
			Double annualEsiByEmplyr = salary.getPfCompContrbtn() * 12D;
			CSContent.addCell(getCellContent(annualEsiByEmplyr.toString(), Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("Leave Encashment"));
			CSContent.addCell(getCellContent(salary.getLeaveEncash().toString(), Rectangle.ALIGN_RIGHT));
			Double annualLeaveEncash = salary.getLeaveEncash() * 12D;
			CSContent.addCell(getCellContent(annualLeaveEncash.toString(), Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("Medical Insurance"));
			CSContent.addCell(getCellContent("-", Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("-", Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent("Gratuity"));
			CSContent.addCell(getCellContent(salary.getGratuity().toString(), Rectangle.ALIGN_RIGHT));
			Double annualGratuity = salary.getGratuity() * 12D;
			CSContent.addCell(getCellContent(annualGratuity.toString(), Rectangle.ALIGN_RIGHT));
			CSContent.addCell(getCellContent(""));
			CSContent.addCell(getCellContent(""));
			CSContent.addCell(getCellContent(""));
			CSContent.addCell(getCellContent("Gross CTC", Rectangle.ALIGN_LEFT, bold10Font, BaseColor.GRAY));
			CSContent.addCell(
					getCellContent(salary.getGrossCTC().toString(), Rectangle.ALIGN_RIGHT, bold10Font, BaseColor.GRAY));
			Double annualGrossCTC = salary.getGrossCTC() * 12D;
			CSContent.addCell(
					getCellContent(annualGrossCTC.toString(), Rectangle.ALIGN_RIGHT, bold10Font, BaseColor.GRAY));

		}
		return CSContent;
	}

	private PdfPTable getNameAndDesignation(Employee employee) {
		PdfPTable nameAndDesig = new PdfPTable(2);
		String firstName = employee.getEmployeeMaster() == null ? ""
				: employee.getEmployeeMaster().getFirstName() == null ? ""
						: employee.getEmployeeMaster().getFirstName();
		String middleName = employee.getEmployeeMaster() == null ? ""
				: employee.getEmployeeMaster().getMiddleName() == null ? ""
						: employee.getEmployeeMaster().getMiddleName();
		String lastName = employee.getEmployeeMaster() == null ? ""
				: employee.getEmployeeMaster().getLastName() == null ? ""
						: employee.getEmployeeMaster().getLastName() + ",";
		String name = firstName + middleName + lastName;
		String designationText = employee.getEmployeeMaster().getDesignation().getDescription();
		nameAndDesig.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		nameAndDesig.setSpacingAfter(30f);
		nameAndDesig.addCell(new Phrase("Name", normal10Font));
		nameAndDesig.addCell(new Phrase("-  " + name, bold10Font));
		nameAndDesig.addCell(new Phrase("Designation", normal10Font));
		nameAndDesig.addCell(new Phrase("-  " + designationText, bold10Font));
		return nameAndDesig;
	}

	private PdfPCell getPageHeading(String headingText) {
		Phrase heading = new Phrase(headingText, bold12Font);
		PdfPCell headingCell = new PdfPCell(heading);
		headingCell.setBorder(Rectangle.NO_BORDER);
		headingCell.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
		headingCell.setPaddingBottom(50f);
		return headingCell;
	}
}
