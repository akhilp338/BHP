package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.MasterTask;
import com.belhopat.backoffice.model.Task;

/**
 * @author BHP_DEV Data repository for employee entity
 *
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, DataTablesRepository<Task, Long> {

	public Task findByMasterTask(MasterTask masterTask);

	@Query( "select tl from TaskList tl where tl.completed!=1 and tl.task.taskOwnerRole IN(:roles)" )
	public List<Task> findByTaskOwner(@Param( "roles" )List<String> userRoles);
}
