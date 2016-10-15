package com.belhopat.backoffice.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.belhopat.backoffice.dto.UploadResponse;

@Service
public interface ExcelService {

	UploadResponse uploadExcel(String type, MultipartFile file) throws IOException;
}
