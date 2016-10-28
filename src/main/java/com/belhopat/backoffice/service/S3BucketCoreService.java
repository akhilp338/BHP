package com.belhopat.backoffice.service;

import java.io.ByteArrayInputStream;
import java.io.File;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.S3BucketFileDTO;
import com.belhopat.backoffice.model.S3BucketFile;

@Service
public interface S3BucketCoreService {

	public boolean uploadFile(S3BucketFileDTO s3BucketFile) throws Exception;

	public boolean uploadFile(S3BucketFile s3BucketFile, ByteArrayInputStream byteContent) throws Exception;

	public byte[] downloadFile(S3BucketFileDTO s3BucketFile) throws Exception;

	public boolean deleteFile(S3BucketFileDTO s3BucketFile);

	public File getFile(S3BucketFileDTO s3BucketFile) throws Exception;

}
