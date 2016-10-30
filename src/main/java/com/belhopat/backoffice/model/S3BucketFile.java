package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "S3_BUCKET_FILE")
public class S3BucketFile extends BaseEntity {

	@Column(name = "BKT_NAME")
	private String bucketName;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "CNT_TYPE")
	private String contentType;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Transient
	private byte[] bytes;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getKey() {
		String key = getUserId() + "/" + getFileType() + "/" + getContentType() + "/" + getFileName() + "."
				+ getContentType();
		return key;
	}

}
