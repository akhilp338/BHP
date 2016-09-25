package com.belhopat.backoffice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.MasterTasks;
import com.belhopat.backoffice.model.Reimburse;
import com.belhopat.backoffice.model.TaskList;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.MasterTasksRepository;
import com.belhopat.backoffice.repository.ReimburseRepository;
import com.belhopat.backoffice.repository.TaskListRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.service.ReimburseService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.TaskConstants;
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
	ReimburseRepository reimburseRepository;

	@Autowired
	TaskListRepository taskListRepository;

	@Autowired
	MasterTasksRepository masterTasksRepository;

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
		createReimburseVerificationTask(reimburse);
		mailService.sendReimburseVerificationMail(reimburse);
	}

	private void createReimburseVerificationTask(Reimburse reimburse) {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		TaskList verificationTask = new TaskList();
		MasterTasks task = masterTasksRepository.findByTaskKey(TaskConstants.REIMBURSE_VERIF);
		verificationTask.setBaseAttributes(currentUser);
		verificationTask.setCompleted(false);
		verificationTask.setTask(task);
		verificationTask.setTaskEntityId(reimburse.getId());
		verificationTask.setStatus(TaskConstants.CREATED);
		taskListRepository.save(verificationTask);
	}
}
