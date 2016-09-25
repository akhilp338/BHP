package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Reimburse;

/**
 * @author BHP_DEV Data repository for employee entity
 *
 */
@Repository
public interface ReimburseRepository extends JpaRepository<Reimburse, Long>, DataTablesRepository<Reimburse, Long> {

	Reimburse findById(Long reimburseId);

	List<Reimburse> findByIdIn(List<Long> reimburseIds);

}
