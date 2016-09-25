package com.belhopat.backoffice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.MasterTasks;
import com.belhopat.backoffice.model.Reimburse;
import com.belhopat.backoffice.model.TaskList;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.MasterTasksRepository;
import com.belhopat.backoffice.repository.TaskListRepository;
import com.belhopat.backoffice.service.TaskService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.TaskConstants;

/**
 * @author BHP_DEV service implementation for dashboard task functionalities
 *
 */
@Component
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskListRepository taskListRepository;

	@Autowired
	MasterTasksRepository masterTasksRepository;

	@Override
	public void createReimburseVerificationTask(Reimburse reimburse) {
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

	@Override
	public void createReimburseApprovalTask(Reimburse reimburse) {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		TaskList approvalTask = new TaskList();
		MasterTasks task = masterTasksRepository.findByTaskKey(TaskConstants.REIMBURSE_APPR);
		approvalTask.setBaseAttributes(currentUser);
		approvalTask.setCompleted(false);
		approvalTask.setTask(task);
		approvalTask.setTaskEntityId(reimburse.getId());
		approvalTask.setStatus(TaskConstants.CREATED);
		taskListRepository.save(approvalTask);
	}

}
