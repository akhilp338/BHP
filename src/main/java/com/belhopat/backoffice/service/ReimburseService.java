package com.belhopat.backoffice.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.belhopat.backoffice.dto.TaskDTO;
import com.belhopat.backoffice.model.Expense;
import com.belhopat.backoffice.model.Reimburse;

@Service
public interface ReimburseService {

	public ResponseEntity<Map<String, String>> saveOrUpdateReimburse(List<Expense> expences);

	public ResponseEntity<Reimburse> getReimburse(Long reimburseId);

	public ResponseEntity<String> approveOrRejectReimburseTask(TaskDTO taskDTO);
}
