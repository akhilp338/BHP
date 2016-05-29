package com.belhopat.backoffice.pdf;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

public class OfferLetterPDF extends BasePDFGenerator {
	public void getPDFContents(Document document)
			throws MalformedURLException, IOException, DocumentException, ParseException {
		document.add(new PdfPTable(1));
	}
}
