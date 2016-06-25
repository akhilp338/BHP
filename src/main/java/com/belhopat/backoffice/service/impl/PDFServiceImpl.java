package com.belhopat.backoffice.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import org.springframework.stereotype.Component;

import com.belhopat.backoffice.pdf.OfferLetterPDF;
import com.belhopat.backoffice.service.PDFService;
import com.itextpdf.text.DocumentException;

/**
 * @author BHP_DEV service implementation for PDF functionalities
 *
 */
@Component
public class PDFServiceImpl implements PDFService {

	@Override
	public byte[] generateOfferLetterPDF()
			throws MalformedURLException, DocumentException, IOException, ParseException {
		OfferLetterPDF pdfGenerator = new OfferLetterPDF();
		pdfGenerator.getPDFContents();
		byte[] pdfByte = null;
		return pdfByte;
	}
}
