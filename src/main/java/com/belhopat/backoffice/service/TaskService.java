package com.belhopat.backoffice.service;

import org.springframework.stereotype.Service;

import com.belhopat.backoffice.model.Reimburse;

@Service
public interface TaskService {

	public void createReimburseVerificationTask(Reimburse reimburse);

	public void createReimburseApprovalTask(Reimburse reimburse);

}
