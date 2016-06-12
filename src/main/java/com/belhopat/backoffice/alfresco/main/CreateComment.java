package com.belhopat.backoffice.alfresco.main;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;

/**
 * Shows how to use CMIS to create a document using the Alfresco Public API.
 * Also uses the REST API to like a folder and comment on a document.
 *
 * @author jpotts
 *
 */
public class CreateComment extends BaseDeviceFns {

    public static void main(String[] args) {
        CreateComment ccde = new CreateComment();
        try {
            ccde.doExample();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doExample() {
        try {
            // Find the root folder of our target site
            String rootFolderId = getRootFolderId(getSite());

            // Create a new folder in the root folder
            Folder subFolder = createFolder(rootFolderId, getFolderName());

            // Create a test document in the subFolder
            Document document = createDocument(subFolder, getLocalFile(), getLocalFileType(), null);

            // Create a comment on the test document
            // NOTE: When dealing with documents, the REST API wants a versionSeriesID!
            comment(document.getVersionSeriesId(), "Here is a comment!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
