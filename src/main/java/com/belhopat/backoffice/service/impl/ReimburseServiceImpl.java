package com.belhopat.backoffice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.dto.TaskDTO;
import com.belhopat.backoffice.model.Currency;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.Expense;
import com.belhopat.backoffice.model.MasterTask;
import com.belhopat.backoffice.model.Reimburse;
import com.belhopat.backoffice.model.Task;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.CurrencyRepository;
import com.belhopat.backoffice.repository.MasterTaskRepository;
import com.belhopat.backoffice.repository.ReimburseRepository;
import com.belhopat.backoffice.repository.TaskRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.EmployeeService;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.service.ReimburseService;
import com.belhopat.backoffice.service.TaskService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.Constants;
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
	TaskService taskService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	MasterTaskRepository masterTaskRepository;

	@Autowired
	ReimburseRepository reimburseRepository;

	@Autowired
	CurrencyRepository currencyRepository;

	@Override
	public ResponseEntity<Map<String, String>> saveOrUpdateReimburse(List<Expense> expenses) {
		Map<String, String> responseMap = new HashMap<>();
		Employee loggedInEmployee = employeeService.getloggedInEmployeeAsEnity();
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		Reimburse reimburse = new Reimburse();
		Long increment = baseService.getSequenceIncrement( reimburse.getClass() );
		String reimburseId = SequenceGenerator.generateReimburseId(increment);
		reimburse.setReimburseId(reimburseId);
		reimburse.setExpenses(expenses);
		reimburse.setEmployee(loggedInEmployee);
		reimburse.setBaseAttributes(loggedInUser);
		reimburseRepository.save(reimburse);
		sendReimburseVerificationRequest(reimburse);
		return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, List<?>>> getDropDownData() {
		Map<String, List<?>> dropDownMap = new HashMap<>();
		List<Currency> currencies = currencyRepository.findAll();
		dropDownMap.put(Constants.CURRENCY, currencies);
		return new ResponseEntity<Map<String, List<?>>>(dropDownMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Reimburse> getReimburse(Long reimburseId) {

		if (reimburseId != null) {
			Reimburse reimburse = reimburseRepository.findById(reimburseId);
			return new ResponseEntity<Reimburse>(reimburse, HttpStatus.OK);
		}
		return new ResponseEntity<Reimburse>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<String> approveOrRejectReimburseTask(TaskDTO taskDTO) {
		if (taskDTO.getTaskId() != null) {
			User currentUser = SessionManager.getCurrentUserAsEntity();
			Task task = taskRepository.findById(taskDTO.getTaskId());
			task.setCompleted(true);
			task.setStatus(taskDTO.getAction());
			task.setUpdateAttributes(currentUser);
			taskRepository.save(task);
			Reimburse reimburse = reimburseRepository.findById(task.getTaskEntityId());
			if (taskDTO.getAction().equals(TaskConstants.REJECTED)) {
				mailService.sendReimburseRejectionMail(reimburse);
			}
			if (taskDTO.getAmount() != null) {
				reimburse.setEligibleAmount(taskDTO.getAmount());
				reimburseRepository.save(reimburse);
			}
			if (task.getMasterTask().getTaskKey().equals(TaskConstants.REIMBURSE_BU_APPR)) {
				mailService.sendReimburseApprovalMail(reimburse);
			}
			createNextReimburseTask(task);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private void sendReimburseVerificationRequest(Reimburse reimburse) {
		mailService.sendReimburseRequestMail(reimburse);
		createReimburseTask(reimburse.getId(), TaskConstants.REIMBURSE_HRM_APPR);
		mailService.sendReimburseVerificationMail(reimburse);
	}

	private void createReimburseTask(Long reimburseId, String mstTaskKey) {
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

	private void createReimburseTask(Long reimburseId, MasterTask mstTask) {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		Task task = new Task();
		task.setBaseAttributes(currentUser);
		task.setCompleted(false);
		task.setMasterTask(mstTask);
		task.setTaskEntityId(reimburseId);
		task.setStatus(TaskConstants.CREATED);
		taskRepository.save(task);
	}

	private void createNextReimburseTask(Task task) {
		Long taskId = task.getMasterTask().getNextTaskId();
		if (taskId != null && task.getStatus() == TaskConstants.APPROVED) {
			MasterTask mstTask = masterTaskRepository.findById(taskId);
			if (mstTask.getTaskKey().equals(TaskConstants.REIMBURSE_BU_APPR)) {
				Reimburse reimburse = reimburseRepository.findById(task.getTaskEntityId());
				if (reimburse.getEligibleAmount() > 5000) {
					createReimburseTask(task.getTaskEntityId(), mstTask);
				}
			} else {
				createReimburseTask(task.getTaskEntityId(), mstTask);
			}
		}
	}
}
