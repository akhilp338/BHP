package com.belhopat.backoffice.alfresco.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.EmployeeSalary;

/**
 * Shows how to use CMIS to create a document using the Alfresco Public API.
 * Also uses the REST API to like a folder and comment on a document.
 *
 * @author jpotts
 *
 */
@Component
public class AlfrescoUploadService extends BaseCloudFns {

	public String uploadFileByCategory(byte[] offerLetter, EmployeeSalary employeeSalary, String category) {
		String docName = "";
		try {
			String rootFolderId = getRootFolderId(getSite());
			// Folder subFolder = createFolder(rootFolderId, getFolderName());
			Folder subFolder = createFolder(rootFolderId, category);
			// RefNo_Belhopat Offer Letter_CandidateNo_Name
			File offerLetterPDF = new File(employeeSalary.getCandidate().getId().toString() + "_Belhopat Offer Letter_"+
					employeeSalary.getCandidate().getCandidateId() + "_" + employeeSalary.getCandidate().getFirstName()
					+ employeeSalary.getCandidate().getMiddleName()
					+ employeeSalary.getCandidate().getLastName()+".pdf");
			FileOutputStream fos = new FileOutputStream(offerLetterPDF);
			fos.write(offerLetter);
			fos.close();
			Document doc = createDocument(subFolder, offerLetterPDF, getLocalFileType());
			docName = doc.getName();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return docName;
	}

	public byte[] getBytesByNameAndCategory(String category, String fileName) throws IOException {
		String rootFolderId = getRootFolderId(getSite());
		Folder folder = getFolder(rootFolderId, category);
		Document document = getFileByName(folder, fileName);
		System.out.println(document.getName());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(document.getContentStream().getStream().read());
		byte[] fileBytes = baos.toByteArray();
		document.getContentStream().getStream().close();
		baos.close();
		return fileBytes;
	}

	public Document getDocumentByNameAndCategory(String category, String fileName) throws IOException {
		String rootFolderId = getRootFolderId(getSite());
		Folder folder = getFolder(rootFolderId, category);
		Document document = getFileByName(folder, fileName);
		System.out.println(document.getName());
		return document;
	}

	public byte[] getBytesFromDocument(Document document) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(document.getContentStream().getStream().read());
		byte[] fileBytes = baos.toByteArray();
		document.getContentStream().getStream().close();
		baos.close();
		return fileBytes;
	}

}
