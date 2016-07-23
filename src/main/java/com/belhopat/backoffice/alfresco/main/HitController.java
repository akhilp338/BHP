package com.belhopat.backoffice.alfresco.main;

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
public class HitController extends BaseCloudFns {

    public void uploadFileByCategory(byte[] offerLetter, EmployeeSalary employeeSalary, String category) {
        try {
            String rootFolderId = getRootFolderId(getSite());
//            Folder subFolder = createFolder(rootFolderId, getFolderName());
            Folder subFolder = createFolder(rootFolderId, category);
            //RefNo_Belhopat Offer Letter_CandidateNo_Name
            File tempFile = File.createTempFile(employeeSalary.getCandidate().getCandidateId() + "_Belhopat Offer Letter_",
            		employeeSalary.getCandidate().getCandidateId()+"_"+employeeSalary.getCandidate().getFirstName()+
            		employeeSalary.getCandidate().getMiddleName()+employeeSalary.getCandidate().getLastName(), null);
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(offerLetter);
            fos.close();
            tempFile.deleteOnExit();
            Document doc = createDocument(subFolder, tempFile, getLocalFileType());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
