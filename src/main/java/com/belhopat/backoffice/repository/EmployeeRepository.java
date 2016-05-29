package com.belhopat.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.belhopat.backoffice.model.Employee;

/**
 * @author BHP_DEV Data repository for employee entity
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, DataTablesRepository<Employee, Long> {

	Employee findById(Long candidateId);

	List<Employee> findByIdIn(List<Long> candidateIds);

	@Query("select e from Employee e where e.employeeMaster.designation.id in (:employeeDesgLookupList)")
	List<Employee> fetchEmployeeWithDesig(@Param("employeeDesgLookupList") Long employeeDesgLookId);

	@Query("select e from Employee e where e.employeeMaster.designation.code=:designation")
	List<Employee> findByDesignation(@Param("designation") String designation);

}
