package com.belhopat.backoffice.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.belhopat.backoffice.dto.TaskDTO;
import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Expense;
import com.belhopat.backoffice.model.Reimburse;

@Service
public interface ReimburseService {

	public ResponseEntity<Map<String, String>> saveOrUpdateReimburse(List<Expense> expences);

	public ResponseEntity<Reimburse> getReimburse(Long reimburseId);

	public ResponseEntity<String> approveOrRejectReimburseTask(TaskDTO taskDTO);

	public ResponseEntity<Map<String, List<?>>> getDropDownData();

	public UploadResponse uploadReimburseFile(MultipartFile file, Long reimburseId)
			throws IllegalStateException, IOException, Exception;
}
