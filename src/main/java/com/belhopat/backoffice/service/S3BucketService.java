package com.belhopat.backoffice.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public interface S3BucketService {

	public void downloadFile(Long s3BucketFileId, HttpServletResponse response) throws Exception;

	public void downloadFiles(List<Long> s3BucketFileIds, HttpServletResponse response) throws Exception;

}
