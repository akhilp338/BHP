package com.belhopat.backoffice.alfresco.main;

import java.io.IOException;

import org.apache.chemistry.opencmis.client.api.Folder;

public class CmisCreateDocument extends BaseDeviceFns{
	 public static void main(String[] args) {
	        CmisCreateDocument ccde = new CmisCreateDocument();
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
	            createDocument(subFolder, getLocalFile(), getLocalFileType());

	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
	    }
}
