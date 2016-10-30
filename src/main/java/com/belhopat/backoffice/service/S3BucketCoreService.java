package com.belhopat.backoffice.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.S3BucketFile;

@Service
public interface S3BucketCoreService {

	public boolean uploadFile(S3BucketFile s3BucketFile, ByteArrayInputStream byteContent) throws Exception;

	public byte[] downloadFile(S3BucketFile s3BucketFile) throws Exception;

	public List<S3BucketFile> downloadFiles(List<S3BucketFile> s3BucketFiles) throws Exception;

	public boolean deleteFile(S3BucketFile s3BucketFile);

}
