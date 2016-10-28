package com.belhopat.backoffice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.S3BucketService;

@Controller
@RequestMapping("/api/aws")
public class S3BucketController {

	@Autowired
	BaseService baseService;

	@Autowired
	S3BucketService s3BucketService;

	@ResponseBody
	@RequestMapping(value = "/uploadAttendanceExcel", method = RequestMethod.POST)
	public UploadResponse uploadAttendanceExcel(@RequestParam("file") MultipartFile file) throws IOException {
		UploadResponse response = new UploadResponse();
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/uploadReimburseFile", method = RequestMethod.POST)
	public UploadResponse uploadReimburseFile(@RequestParam("file") MultipartFile file) throws IOException {
		UploadResponse response = new UploadResponse();
		return response;
	}

}
