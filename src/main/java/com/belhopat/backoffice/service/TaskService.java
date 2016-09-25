package com.belhopat.backoffice.service;

import org.springframework.stereotype.Service;

@Service
public interface TaskService {

	public void createReimburseTask(Long reimburseId, String mstTask);

}
