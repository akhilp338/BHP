package com.belhopat.backoffice.dto;

import java.io.ByteArrayInputStream;

public class S3BucketFileDTO {

	private String bucketName;

	private String key;

	private ByteArrayInputStream content;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ByteArrayInputStream getContent() {
		return content;
	}

	public void setContent(ByteArrayInputStream content) {
		this.content = content;
	}

}