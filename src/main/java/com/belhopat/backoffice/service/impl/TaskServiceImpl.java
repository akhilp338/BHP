package com.belhopat.backoffice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.model.MasterTask;
import com.belhopat.backoffice.model.Task;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.MasterTaskRepository;
import com.belhopat.backoffice.repository.TaskRepository;
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
	TaskRepository taskRepository;

	@Autowired
	MasterTaskRepository masterTaskRepository;

	@Override
	public void createReimburseTask(Long reimburseId, String mstTaskKey) {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		Task task = new Task();
		MasterTask mstTask = masterTaskRepository.findByTaskKey(mstTaskKey);
		task.setBaseAttributes(currentUser);
		task.setCompleted(false);
		task.setMasterTask(mstTask);
		task.setTaskEntityId(reimburseId);
		task.setStatus(TaskConstants.CREATED);
		taskRepository.save(task);
	}

}
