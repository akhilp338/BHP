package com.belhopat.backoffice.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.ParseException;

import org.springframework.stereotype.Component;

import com.belhopat.backoffice.pdf.BasePDFGenerator;
import com.belhopat.backoffice.pdf.HeaderFooterEvent;
import com.belhopat.backoffice.pdf.OfferLetterPDF;
import com.belhopat.backoffice.service.PDFService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author BHP_DEV service implementation for PDF functionalities
 *
 */
@Component
public class PDFServiceImpl implements PDFService {

	@Override
	public byte[] generateOfferLetterPDF()
			throws MalformedURLException, DocumentException, IOException, ParseException {
		BasePDFGenerator pdfGenerator = new OfferLetterPDF();
		String fileName = "OfferLetter.pdf";
		getGeneratedPDFTest(fileName, pdfGenerator);
		byte[] file = null;
//		byte[] file = getGeneratedPDF(fileName, pdfGenerator);
		return file;
	}

	private byte[] getGeneratedPDF(String fileName, BasePDFGenerator pdfGenerator)
			throws DocumentException, MalformedURLException, IOException, ParseException {
		ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4, 50f, 50f, 150f, 60f);
		PdfWriter writer = PdfWriter.getInstance(document, baOutputStream);
		writer.setBoxSize("art", PageSize.A4);
		HeaderFooterEvent event = new HeaderFooterEvent();
		writer.setPageEvent(event);
		document.open();
		pdfGenerator.getPDFContents(document);
		document.close();
		baOutputStream.close();
		return baOutputStream.toByteArray();
	}

	private void getGeneratedPDFTest(String fileName, BasePDFGenerator pdfGenerator)
			throws DocumentException, MalformedURLException, IOException, ParseException {
		OutputStream outputStream = new FileOutputStream("/home/sujith/Desktop/" + fileName);
		Document document = new Document(PageSize.A4, 50f, 50f, 150f, 60f);
		PdfWriter writer = PdfWriter.getInstance(document, outputStream);
		writer.setBoxSize("art", PageSize.A4);
		HeaderFooterEvent event = new HeaderFooterEvent();
		writer.setPageEvent(event);
		document.open();
		pdfGenerator.getPDFContents(document);
		document.close();
		outputStream.close();
	}
}
