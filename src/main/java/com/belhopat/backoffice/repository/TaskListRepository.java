package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.MasterTasks;
import com.belhopat.backoffice.model.TaskList;

/**
 * @author BHP_DEV Data repository for employee entity
 *
 */
@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long>, DataTablesRepository<TaskList, Long> {

	public TaskList findByTask(MasterTasks masterTasks);
}
