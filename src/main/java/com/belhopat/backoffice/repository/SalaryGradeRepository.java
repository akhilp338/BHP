package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.SalaryGrade;

/**
 * @author BHP_DEV
 * Data repository for SalaryGrade entity 
 *
 */
@Repository
public interface SalaryGradeRepository extends JpaRepository<SalaryGrade, Long> {
	
	public SalaryGrade findByGrade(String grade);


}
