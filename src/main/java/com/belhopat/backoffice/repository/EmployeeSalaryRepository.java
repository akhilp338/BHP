package com.belhopat.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.EmployeeSalary;

/**
 * @author BHP_DEV
 * Data repository for EmployeeSalary entity 
 *
 */
@Repository
public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Long> {
	


}
