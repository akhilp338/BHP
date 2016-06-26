package com.belhopat.backoffice.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.MasterRoles;
import com.belhopat.backoffice.model.MasterTasks;
import com.belhopat.backoffice.model.TaskList;

/**
 * @author BHP_DEV Data repository for employee entity
 *
 */
@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long>, DataTablesRepository<TaskList, Long> {

	public TaskList findByTask(MasterTasks masterTasks);

	@Query( "select tl from TaskList tl where tl.completed!=1 and tl.task.taskOwnerRole IN(:roles)" )
	public List<TaskList> findByTaskOwner(@Param( "roles" )List<String> userRoles);
}
