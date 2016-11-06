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
import com.belhopat.backoffice.repository.TaskRepository;
import com.belhopat.backoffice.service.TaskService;

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
		// Specification<Task> specification = getDashboardTaskSpecs(null);
		DataTablesOutput<Task> tasks = taskRepository.findAll(input);
		return tasks;
	}

	private Specification<Task> getDashboardTaskSpecs(Boolean completed) {
		Specification<Task> specification = new Specification<Task>() {
			@Override
			public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isNotDeleted = criteriaBuilder.equal(root.get("deleted"), false);
				if (completed != null) {
					Predicate isCompleted = criteriaBuilder.equal(root.get("completed"), completed);
					return criteriaBuilder.and(isNotDeleted, isCompleted);
				}
				return criteriaBuilder.and(isNotDeleted);
			}
		};
		return specification;
	}

	@Override
	public DashboardCount getDashboardCount() {
		Long completed = taskRepository.count(getDashboardTaskSpecs(true));
		Long pending = taskRepository.count(getDashboardTaskSpecs(false));
		DashboardCount dashboardCount = new DashboardCount();
		dashboardCount.setCompleted(completed);
		dashboardCount.setPending(pending);
		return dashboardCount;
	}

}
