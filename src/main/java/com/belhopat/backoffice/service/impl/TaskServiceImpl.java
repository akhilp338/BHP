package com.belhopat.backoffice.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.dto.DashboardCount;
import com.belhopat.backoffice.model.Task;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.TaskRepository;
import com.belhopat.backoffice.service.TaskService;
import com.belhopat.backoffice.session.SessionManager;

/**
 * @author BHP_DEV service implementation for dashboard task functionalities
 *
 */
@Component
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Override
	public DataTablesOutput<Task> getDashboardTasks(DataTablesInput input) {
		User loggerinUser = SessionManager.getCurrentUserAsEntity();
		Specification<Task> specification = new Specification<Task>() {
			@Override
			public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isNotDeleted = criteriaBuilder.equal(root.get("deleted"), false);
				return criteriaBuilder.and(isNotDeleted);
			}
		};
		DataTablesOutput<Task> tasks = taskRepository.findAll(input);
		return tasks;
	}

	@Override
	public DashboardCount getDashboardCount() {
		DashboardCount dashboardCount = new DashboardCount();
		dashboardCount.setCompleted(10);
		dashboardCount.setCritical(15);
		dashboardCount.setMediumCritical(9);
		dashboardCount.setNonCritical(50);
		return dashboardCount;
	}

}
