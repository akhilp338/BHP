package com.belhopat.backoffice.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	public void downloadFile(@RequestParam Long s3BucketFileId, HttpServletResponse response) throws Exception {
		s3BucketService.downloadFile(s3BucketFileId, response);
	}

	@ResponseBody
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	public void downloadFiles(@RequestBody List<Long> s3BucketFileIds, HttpServletResponse response) throws Exception {
		s3BucketService.downloadFiles(s3BucketFileIds, response);
	}

}
