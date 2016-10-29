package com.belhopat.backoffice.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.belhopat.backoffice.dto.S3BucketFileDTO;
import com.belhopat.backoffice.model.S3BucketFile;
import com.belhopat.backoffice.service.S3BucketCoreService;
import com.belhopat.backoffice.util.Constants;

/**
 * @author BHP_DEV service implementation for general functionalities
 */
@Component
public class S3BucketCoreServiceImpl implements S3BucketCoreService {

	protected static final Logger LOGGER = Logger.getLogger(S3BucketCoreServiceImpl.class.getName());

	@Override
	public boolean uploadFile(S3BucketFileDTO s3BucketFile) throws Exception {
		AWSCredentials credentials = new BasicAWSCredentials(Constants.AWS_ACCEESS_KEY_ID, Constants.AWS_SECRET_KEY);
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		try {
			s3Client.putObject(new PutObjectRequest(s3BucketFile.getBucketName(), s3BucketFile.getKey(),
					s3BucketFile.getContent(), new ObjectMetadata()));
			s3Client.setObjectAcl(s3BucketFile.getBucketName(), s3BucketFile.getKey(),
					CannedAccessControlList.PublicRead);
			return true;
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return false;
	}

	@Override
	public boolean uploadFile(S3BucketFile s3BucketFile, ByteArrayInputStream byteContent) throws Exception {
		String key = s3BucketFile.getUserId() + "/" + s3BucketFile.getFileType() + "/" + s3BucketFile.getContentType()
				+ "/" + s3BucketFile.getFileName() + "." + s3BucketFile.getContentType();
		S3BucketFileDTO s3BucketFileDTO = new S3BucketFileDTO();
		s3BucketFileDTO.setContent(byteContent);
		s3BucketFileDTO.setKey(key);
		s3BucketFileDTO.setBucketName(s3BucketFile.getBucketName());
		boolean status = uploadFile(s3BucketFileDTO);
		return status;
	}

	@Override
	public byte[] downloadFile(S3BucketFileDTO s3BucketFile) throws Exception {
		AWSCredentials credentials = new BasicAWSCredentials(Constants.AWS_ACCEESS_KEY_ID, Constants.AWS_SECRET_KEY);
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		byte[] bytes = null;
		try {
			GetObjectRequest getObject = new GetObjectRequest(s3BucketFile.getBucketName(), s3BucketFile.getKey());
			S3Object s3object = s3Client.getObject(getObject);
			bytes = IOUtils.toByteArray(s3object.getObjectContent());
			FileUtils.writeByteArrayToFile(new File("/home"), bytes);

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return bytes;
	}

	@Override
	public boolean deleteFile(S3BucketFileDTO s3BucketFile) {
		AWSCredentials credentials = new BasicAWSCredentials(Constants.AWS_ACCEESS_KEY_ID, Constants.AWS_SECRET_KEY);
		AmazonS3 s3Client = new AmazonS3Client(credentials);
		boolean success = false;
		if (s3BucketFile.getKey() != "" || s3BucketFile.getKey() != null) {
			s3Client.deleteObject(s3BucketFile.getBucketName(), s3BucketFile.getKey());
			success = true;
		}
		return success;
	}
}
