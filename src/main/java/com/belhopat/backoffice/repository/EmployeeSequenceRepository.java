package com.belhopat.backoffice.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.EmployeeSequence;
/**
 * @author BHP_DEV
 * Data repository for Sequence entity 
 *
 */
@Repository
public interface EmployeeSequenceRepository extends JpaRepository< EmployeeSequence, Long> {

	@Query("select max(e.createdDate) from EmployeeSequence e")
	Date getLatestDate();

//	@Query("truncate table EmployeeSequence e")
//	void truncate();
}
