package com.belhopat.backoffice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.Reimburse;
import com.belhopat.backoffice.repository.MasterTasksRepository;
import com.belhopat.backoffice.repository.ReimburseRepository;
import com.belhopat.backoffice.repository.TaskListRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.service.ReimburseService;
import com.belhopat.backoffice.service.TaskService;
import com.belhopat.backoffice.util.sequence.SequenceGenerator;

/**
 * @author BHP_DEV Service layer to implement candidates business
 */
@Component
public class ReimburseServiceImpl implements ReimburseService {

	@Autowired
	BaseService baseService;

	@Autowired
	MailService mailService;

	@Autowired
	TaskService taskService;

	@Autowired
	ReimburseRepository reimburseRepository;

	@Override
	public ResponseEntity<Map<String, String>> saveOrUpdateReimburse(Reimburse reimburse) {
		Map<String, String> responseMap = new HashMap<>();
		Long increment = baseService.getSequenceIncrement(Employee.class);
		String reimburseId = SequenceGenerator.generateReimburseId(increment);
		reimburse.setReimburseId(reimburseId);
		reimburseRepository.save(reimburse);
		sendReimburseVerificationRequestToHRM(reimburse);
		mailService.sendReimburseRequestMail(reimburse);
		return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.OK);
	}

	private void sendReimburseVerificationRequestToHRM(Reimburse reimburse) {
		taskService.createReimburseVerificationTask(reimburse);
		mailService.sendReimburseVerificationMail(reimburse);
	}

}
