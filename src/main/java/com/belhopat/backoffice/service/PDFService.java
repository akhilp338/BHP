package com.belhopat.backoffice.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.EmployeeSalary;
import com.itextpdf.text.DocumentException;

@Service
public interface PDFService {

	public byte[] generateOfferLetterPDF(EmployeeSalary employeeSalary)
			throws MalformedURLException, DocumentException, IOException, ParseException;

}
