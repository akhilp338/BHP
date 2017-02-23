package com.belhopat.backoffice.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadDTO {

	private MultipartFile file;

	private String type;

	private Long userId;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UploadDTO [file=" + file + ", type=" + type + ", userId=" + userId + "]";
	}
}
