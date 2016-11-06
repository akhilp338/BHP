package com.belhopat.backoffice.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.belhopat.backoffice.dto.S3BucketFileDTO;
import com.belhopat.backoffice.service.S3BucketCoreService;
import com.belhopat.backoffice.service.S3BucketService;

@Component
public class S3BucketServiceImpl implements S3BucketService {

	@Autowired
	S3BucketCoreService s3BucketCoreService;

	protected static final Logger LOGGER = Logger.getLogger(S3BucketServiceImpl.class.getName());

	@Override
	public void uploadFile(S3BucketFileDTO s3BucketFile) throws Exception {
		AmazonS3 s3Client = new AmazonS3Client();
		s3Client.putObject(new PutObjectRequest(s3BucketFile.getBucketName(), s3BucketFile.getKey(),
				s3BucketFile.getContent(), new ObjectMetadata()));
		s3Client.setObjectAcl(s3BucketFile.getBucketName(), s3BucketFile.getKey(), CannedAccessControlList.PublicRead);
	}

	@Override
	public byte[] downloadFile(S3BucketFileDTO s3BucketFile) throws Exception {
		AmazonS3 s3Client = new AmazonS3Client();
		byte[] bytes = null;
		S3Object s3object = s3Client
				.getObject(new GetObjectRequest(s3BucketFile.getBucketName(), s3BucketFile.getKey()));
		try {

			bytes = IOUtils.toByteArray(s3object.getObjectContent());
			FileUtils.writeByteArrayToFile(new File("/home"), bytes);

		} catch (IOException e) {
			LOGGER.info("fileDownload in S3BucketService :" + e.getStackTrace());
			throw e;

		} catch (AmazonS3Exception amazonS3Exception) {
			LOGGER.info("fileDownload in S3BucketService :" + amazonS3Exception.getStackTrace());
			throw amazonS3Exception;
		}
		return bytes;
	}

	@Override
	public File getFile(S3BucketFileDTO s3BucketFile) throws Exception {
		AmazonS3 s3Client = new AmazonS3Client();
		File file = new File("/home");
		byte[] bytes = null;
		S3Object s3object = s3Client
				.getObject(new GetObjectRequest(s3BucketFile.getBucketName(), s3BucketFile.getKey()));
		try {

			bytes = IOUtils.toByteArray(s3object.getObjectContent());
			FileUtils.writeByteArrayToFile(file, bytes);

		} catch (IOException e) {
			LOGGER.info("fileDownload in S3BucketService :" + e.getStackTrace());
			throw e;

		} catch (AmazonS3Exception amazonS3Exception) {
			LOGGER.info("fileDownload in S3BucketService :" + amazonS3Exception.getStackTrace());
			throw amazonS3Exception;
		}
		return file;
	}

	@Override
	public boolean deleteFile(S3BucketFileDTO s3BucketFile) {
		AmazonS3 s3Client = new AmazonS3Client();
		boolean success = false;
		if (s3BucketFile.getKey() != "" || s3BucketFile.getKey() != null) {
			s3Client.deleteObject(s3BucketFile.getBucketName(), s3BucketFile.getKey());
			success = true;
		}
		return success;
	}
}