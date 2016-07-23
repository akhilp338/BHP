package com.belhopat.backoffice.alfresco.main;

import java.io.File;
import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.springframework.stereotype.Component;

/**
 * Shows how to use CMIS to create a document using the Alfresco Public API.
 * Also uses the REST API to like a folder and comment on a document.
 *
 * @author jpotts
 *
 */
@Component
public class HitController extends BaseCloudFns {

    public void doExample(byte[] offerLetter) {
        try {
            // Find the root folder of our target site
            String rootFolderId = getRootFolderId(getSite());

            // Create a new folder in the root folder
            Folder subFolder = createFolder(rootFolderId, getFolderName());

            // Like the folder
//            like(subFolder.getId());
            File file = new File("///home/shinto/Downloads/Collect.pdf");
            Document doc = createDocument(subFolder, file, getLocalFileType());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
