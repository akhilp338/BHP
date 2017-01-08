package com.belhopat.backoffice.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadDTO {

	private List<MultipartFile> files;

	private String name;

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UploadDTO [files=" + files + ", name=" + name + "]";
	}
}
