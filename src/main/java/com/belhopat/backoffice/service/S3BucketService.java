package com.belhopat.backoffice.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.S3BucketFileDTO;

@Service
public interface S3BucketService {

	public void uploadFile(S3BucketFileDTO s3BucketFile) throws Exception;

	public byte[] downloadFile(S3BucketFileDTO s3BucketFile) throws Exception;

	public boolean deleteFile(S3BucketFileDTO s3BucketFile);

	public File getFile(S3BucketFileDTO s3BucketFile) throws Exception;

}
