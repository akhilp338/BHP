package com.belhopat.backoffice.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.S3BucketFile;
import com.belhopat.backoffice.repository.S3BucketFileRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.S3BucketCoreService;
import com.belhopat.backoffice.service.S3BucketService;
import com.belhopat.backoffice.util.Constants;

@Component
public class S3BucketServiceImpl implements S3BucketService {

	@Autowired
	BaseService baseService;

	@Autowired
	S3BucketCoreService s3BucketCoreService;

	@Autowired
	S3BucketFileRepository s3BucketFileRepository;

	@Override
	public void downloadFile(Long s3BucketFileId, HttpServletResponse response) throws Exception {
		S3BucketFile s3BucketFile = s3BucketFileRepository.findById(s3BucketFileId);
		if (s3BucketFile != null) {
			byte[] bytes = null;
			bytes = s3BucketCoreService.downloadFile(s3BucketFile);
			generateDownloadLink(s3BucketFile, bytes, response);
		}

	}

	@Override
	public void downloadFiles(List<Long> s3BucketFileIds, HttpServletResponse response) throws Exception {
		List<S3BucketFile> s3BucketFiles = s3BucketFileRepository.findAll(s3BucketFileIds);
		if (s3BucketFiles != null) {
			byte[] bytes = null;
			s3BucketFiles = s3BucketCoreService.downloadFiles(s3BucketFiles);
			bytes = zipFile(s3BucketFiles);
			String fileName = "download.zip";
			generateZipDownloadLink(bytes, response, fileName);
		}

	}

	public static void zipFile(final File[] files, final File targetZipFile) throws IOException {
		try {
			FileOutputStream fos = new FileOutputStream(targetZipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			byte[] buffer = new byte[128];
			for (int i = 0; i < files.length; i++) {
				File currentFile = files[i];
				if (!currentFile.isDirectory()) {
					ZipEntry entry = new ZipEntry(currentFile.getName());
					FileInputStream fis = new FileInputStream(currentFile);
					zos.putNextEntry(entry);
					int read = 0;
					while ((read = fis.read(buffer)) != -1) {
						zos.write(buffer, 0, read);
					}
					zos.closeEntry();
					fis.close();
				}
			}
			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found : " + e);
		}

	}

	private void generateDownloadLink(S3BucketFile s3BucketFile, byte[] bytes, HttpServletResponse response)
			throws IOException {
		OutputStream output = response.getOutputStream();
		response.setContentType(s3BucketFile.getContentType());
		response.addHeader(Constants.CONTENT_DISPOSITION, Constants.ATTACHMENT + s3BucketFile.getFileName());
		output.write(bytes);
		output.flush();
		response.flushBuffer();
		output.close();
	}

	private void generateZipDownloadLink(byte[] bytes, HttpServletResponse response, String fileName)
			throws IOException {
		OutputStream output = response.getOutputStream();
		response.setContentType("application/zip");
		response.addHeader(Constants.CONTENT_DISPOSITION, Constants.ATTACHMENT + fileName);
		output.write(bytes);
		output.flush();
		response.flushBuffer();
		output.close();
	}

	private byte[] zipFile(List<S3BucketFile> files) throws IOException {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(baos);
			byte[] buffer = new byte[128];
			for (S3BucketFile file : files) {
				ZipEntry entry = new ZipEntry(file.getFileName());
				ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());
				zos.putNextEntry(entry);
				int read = 0;
				while ((read = bais.read(buffer)) != -1) {
					zos.write(buffer, 0, read);
				}
				zos.closeEntry();
				bais.close();
			}
			bytes = baos.toByteArray();
			zos.close();
			baos.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found : " + e);
		}
		return bytes;
	}
}
