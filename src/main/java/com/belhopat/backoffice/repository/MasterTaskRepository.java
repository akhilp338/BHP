package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.MasterTask;

/**
 * @author BHP_DEV Data repository for employee entity
 *
 */
@Repository
public interface MasterTaskRepository extends JpaRepository<MasterTask, Long>, DataTablesRepository<MasterTask, Long> {

	MasterTask findByTaskKey(String taskName);

	MasterTask findById(Long nextTaskId);



}
