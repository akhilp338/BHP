package com.belhopat.backoffice.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.LookupDetail;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.CandidateRepository;
import com.belhopat.backoffice.repository.EmployeeRepository;
import com.belhopat.backoffice.repository.LookupDetailRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.EmployeeService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.sequence.SequenceGenerator;

/**
 * @author BHP_DEV
 *
 */
@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	BaseService baseService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	LookupDetailRepository lookupDetailRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.EmployeeService#saveOrUpdateEmployee(com.
	 * belhopat.backoffice.model.Employee) saves the employee to the db
	 */
	@Override
	public ResponseEntity<String> saveOrUpdateEmployee(EmployeeDto employeeDto) {
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		Employee hrManager = employeeRepository.findOne(employeeDto.getHrManager());
		Employee accountManager = employeeRepository.findOne(employeeDto.getAccountManager());
		LookupDetail businessUnit = lookupDetailRepository.findOne(employeeDto.getBusinessUnit());
		Candidate employeeMaster = candidateRepository.findOne(employeeDto.getEmployeeMasterId());
		Employee employee = null;
		if (employeeDto.getId() == null) {
			employee = new Employee();
			employee.setBaseAttributes(loggedInUser);
			Long increment = baseService.getSequenceIncrement(Employee.class);
			String employeeId = SequenceGenerator.generateEmployeeId(increment);
			employee.setEmployeeId(employeeId);
		} else {
			employee = employeeRepository.findOne(employeeDto.getId());
			employee.setUpdateAttributes(loggedInUser);
		}

		employee.setAccountManager(accountManager);
		employee.setBusinessUnit(businessUnit);
		employee.setEmployeeMaster(employeeMaster);
		employee.setHrManager(hrManager);
		employee.setJoiningDate(employeeDto.getJoiningDate());
		employee = employeeRepository.save(employee);
		if (employee != null) {
			Candidate candidate = candidateRepository.findById(employeeDto.getEmployeeMasterId());
			candidate.setEmployee(true);
			candidate = candidateRepository.save(candidate);
			if (candidate != null) {
				String employeeName = employee.getEmployeeMaster().getFirstName() + " "
						+ employee.getEmployeeMaster().getLastName();
				return new ResponseEntity<String>(employeeName, HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.belhopat.backoffice.service.EmployeeService#getEmployee(org.
	 * springframework.data.jpa.datatables.mapping.DataTablesInput) gets list of
	 * employee from database
	 */
	@Override
	public DataTablesOutput<Employee> getEmployee(DataTablesInput input) {
		Specification<Employee> specification = new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isNotDeleted = criteriaBuilder.equal(root.get("deleted"), false);
				return criteriaBuilder.and(isNotDeleted);
			}
		};
		DataTablesOutput<Employee> dataTablesOutput = employeeRepository.findAll(input, specification);
		return dataTablesOutput;
	}

	/*
	 * (non-Javadoc) // *
	 * 
	 * @see
	 * com.belhopat.backoffice.service.EmployeeService#getAnEmployee(java.lang.
	 * Long) gets an employee from datatase
	 */
	@Override
	public Employee getAnEmployee(Long id) {
		return employeeRepository.findById(id);
	}

}
