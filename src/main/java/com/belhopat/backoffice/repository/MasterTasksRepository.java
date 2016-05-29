package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.MasterTasks;

/**
 * @author BHP_DEV Data repository for employee entity
 *
 */
@Repository
public interface MasterTasksRepository extends JpaRepository<MasterTasks, Long>, DataTablesRepository<MasterTasks, Long> {

	MasterTasks findByTaskKey(String taskName);



}
