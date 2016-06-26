package com.belhopat.backoffice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.alfresco.main.HitController;
import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.dto.SalaryDTO;
import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.CandidateSequence;
import com.belhopat.backoffice.model.City;
import com.belhopat.backoffice.model.Client;
import com.belhopat.backoffice.model.ClientSequence;
import com.belhopat.backoffice.model.Country;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.EmployeeSalary;
import com.belhopat.backoffice.model.EmployeeSequence;
import com.belhopat.backoffice.model.LookupDetail;
import com.belhopat.backoffice.model.MasterTasks;
import com.belhopat.backoffice.model.SalaryGrade;
import com.belhopat.backoffice.model.Skill;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.model.TaskList;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.CandidateRepository;
import com.belhopat.backoffice.repository.CandidateSequenceRepository;
import com.belhopat.backoffice.repository.CityRepository;
import com.belhopat.backoffice.repository.ClientSequenceRepository;
import com.belhopat.backoffice.repository.CountryRepository;
import com.belhopat.backoffice.repository.EmployeeRepository;
import com.belhopat.backoffice.repository.EmployeeSalaryRepository;
import com.belhopat.backoffice.repository.EmployeeSequenceRepository;
import com.belhopat.backoffice.repository.LookupDetailRepository;
import com.belhopat.backoffice.repository.MasterTasksRepository;
import com.belhopat.backoffice.repository.SalaryGradeRepository;
import com.belhopat.backoffice.repository.SkillRepository;
import com.belhopat.backoffice.repository.StateRepository;
import com.belhopat.backoffice.repository.TaskListRepository;
import com.belhopat.backoffice.repository.UserRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.Constants;
import com.belhopat.backoffice.util.TaskConstants;

/**
 * @author BHP_DEV service implementation for general functionalities
 *
 */
@Component
public class BaseServiceImpl implements BaseService {

	@Autowired
	LookupDetailRepository lookupDetailRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	CandidateSequenceRepository candidateSequenceRepository;

	@Autowired
	EmployeeSequenceRepository employeeSequenceRepository;

	@Autowired
	ClientSequenceRepository clientSequenceRepository;
	
	@Autowired
	MasterTasksRepository masterTasksRepository;
	
	@Autowired
	TaskListRepository taskListRepository;
	
	@Autowired
	SalaryGradeRepository salaryGradeRepository;
	
	@Autowired
	EmployeeSalaryRepository employeeSalaryRepository;

	@Autowired
	HitController controller;
	
	@Autowired
	UserRepository userRepository;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.BaseService#getCandidateDropDownData()
	 * gets candidate dropdown data and creates a map out of it.
	 */
	@Override
	public ResponseEntity<Map<String, List<?>>> getCandidateDropDownData() {
		List<LookupDetail> divisions = lookupDetailRepository.findByLookupKey(Constants.DIVISION);
		List<LookupDetail> designations = lookupDetailRepository.findByLookupKey(Constants.DESIGNATION);
		List<LookupDetail> purposes = lookupDetailRepository.findByLookupKey(Constants.PURPOSE);
		List<LookupDetail> bloodGroups = lookupDetailRepository.findByLookupKey(Constants.BLOOD_GROUP);
		List<LookupDetail> employmentStatuses = lookupDetailRepository.findByLookupKey(Constants.EMPLOYMENT_STATUS);
		List<LookupDetail> familyMembers = lookupDetailRepository.findByLookupKey(Constants.FAMILY_MEMBER);
		List<Skill> skills = skillRepository.findAll();
		List<Country> countries = countryRepository.findAll();
		Map<String, List<?>> dropDownMap = new HashMap<>();
		dropDownMap.put(Constants.DIVISION, divisions);
		dropDownMap.put(Constants.DESIGNATION, designations);
		dropDownMap.put(Constants.PURPOSE, purposes);
		dropDownMap.put(Constants.BLOOD_GROUP, bloodGroups);
		dropDownMap.put(Constants.EMPLOYMENT_STATUS, employmentStatuses);
		dropDownMap.put(Constants.FAMILY_MEMBER, familyMembers);
		dropDownMap.put(Constants.SKILL, skills);
		dropDownMap.put(Constants.COUNTRY, countries);
		return new ResponseEntity<Map<String, List<?>>>(dropDownMap, HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.BaseService#getEmployeeDropDownData()
	 * gets employee dropdown data and creates a map out of it.
	 */
	@Override
	public ResponseEntity<Map<String, List<?>>> getEmployeeDropDownData() {
		List<LookupDetail> divisions = lookupDetailRepository.findByLookupKey(Constants.DIVISION);
		List<LookupDetail> designations = lookupDetailRepository.findByLookupKey(Constants.DESIGNATION);
		List<LookupDetail> purposes = lookupDetailRepository.findByLookupKey(Constants.PURPOSE);
		List<LookupDetail> bloodGroups = lookupDetailRepository.findByLookupKey(Constants.BLOOD_GROUP);
		List<LookupDetail> employmentStatuses = lookupDetailRepository.findByLookupKey(Constants.EMPLOYMENT_STATUS);
		List<LookupDetail> familyMembers = lookupDetailRepository.findByLookupKey(Constants.FAMILY_MEMBER);
		List<Skill> skills = skillRepository.findAll();
		List<Country> countries = countryRepository.findAll();
		Map<String, List<?>> dropDownMap = new HashMap<>();
		dropDownMap.put(Constants.DIVISION, divisions);
		dropDownMap.put(Constants.DESIGNATION, designations);
		dropDownMap.put(Constants.PURPOSE, purposes);
		dropDownMap.put(Constants.BLOOD_GROUP, bloodGroups);
		dropDownMap.put(Constants.EMPLOYMENT_STATUS, employmentStatuses);
		dropDownMap.put(Constants.FAMILY_MEMBER, familyMembers);
		dropDownMap.put(Constants.SKILL, skills);
		dropDownMap.put(Constants.COUNTRY, countries);
		return new ResponseEntity<Map<String, List<?>>>(dropDownMap, HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.BaseService#getStatesByCountry(java.lang.
	 * Long) queries the country repo to get its state
	 */
	@Override
	public ResponseEntity<List<State>> getStatesByCountry(Long countryId) {
		List<State> states = stateRepository.findByCountry(countryId);
		return new ResponseEntity<List<State>>(states, HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.BaseService#getCitiesByState(java.lang.
	 * Long) queries the state repo to get its city
	 */
	@Override
	public ResponseEntity<List<City>> getCitiesByState(Long stateId) {
		List<City> states = cityRepository.findByStateId(stateId);
		return new ResponseEntity<List<City>>(states, HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.BaseService#getUnselectedSkillSet(java.
	 * util.List) Adds or remove the selected/unselected skills in add candidate
	 * form
	 */
	@Override
	public List<Skill> getUnselectedSkillSet(List<Skill> selectedSkillSet) {
		List<Skill> skillSet = skillRepository.findAll();
		if (!selectedSkillSet.isEmpty()) {
			for (Skill skill : selectedSkillSet) {
				if (skillSet.contains(skill))
					skillSet.remove(skill);
			}
		}
		return skillSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.BaseService#getSequenceIncrement(java.
	 * lang.Class) creates and increments the sequence
	 */
	@Override
	public <T> Long getSequenceIncrement(Class<T> clazz) {
		Long increment = null;
		if (clazz.equals(Candidate.class)) {
			CandidateSequence candidateSequence = candidateSequenceRepository.save(new CandidateSequence());
			increment = candidateSequence.getId();
		} else if (clazz.equals(Employee.class)) {
			EmployeeSequence employeeSequence = employeeSequenceRepository.save(new EmployeeSequence());
			increment = employeeSequence.getId();
		} else if (clazz.equals(Client.class)) {
			ClientSequence clientSequence = clientSequenceRepository.save(new ClientSequence());
			increment = clientSequence.getId();
		}
		return increment;
	}

	@Override
	public Map<String, List<?>> getEmployeeDropdowns() {
		Map<String, List<?>> dropDownMap = new HashMap<>();
		dropDownMap.put(Constants.EMP_DESIG_HRM, employeeRepository.findByDesignation(Constants.EMP_DESIG_HRM));
		dropDownMap.put(Constants.EMP_DESIG_HRR, employeeRepository.findByDesignation(Constants.EMP_DESIG_HRR));
		dropDownMap.put(Constants.EMP_DESIG_AM, employeeRepository.findByDesignation(Constants.EMP_DESIG_AM));
		dropDownMap.put(Constants.EMP_DESIG_FM, employeeRepository.findByDesignation(Constants.EMP_DESIG_FM));
		dropDownMap.put(Constants.EMP_DESIG_CEO, employeeRepository.findByDesignation(Constants.EMP_DESIG_CEO));
		dropDownMap.put(Constants.EMP_DESIG_BUH, employeeRepository.findByDesignation(Constants.EMP_DESIG_BUH));
		dropDownMap.put(Constants.CLIENT_STATUS, lookupDetailRepository.findByLookupKey(Constants.CLIENT_STATUS));
		dropDownMap.put(Constants.COUNTRY, countryRepository.findAll());
		dropDownMap.put(Constants.DESIGNATION, lookupDetailRepository.findByLookupKey(Constants.DESIGNATION));
		dropDownMap.put(Constants.DIVISION, lookupDetailRepository.findByLookupKey(Constants.DIVISION));
		return dropDownMap;
	}

	@Override
	public ResponseEntity<List<TaskList>> createOfferLetter(RequestObject requestObject) {
		/*offer letter transaction part*/
		TaskList newTask= createNewTaskList(TaskConstants.OFFER_LETTER_CREATION);
		return null;
	}

	private TaskList createNewTaskList(String taskName) {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		TaskList currentTaskRow = new TaskList();
		TaskList newTaskRow = new TaskList();
		MasterTasks currentTask = masterTasksRepository.findByTaskKey(taskName);
		currentTaskRow.setBaseAttributes(currentUser);
		currentTaskRow.setCompleted((byte)1);
		currentTaskRow.setTask(currentTask);
		currentTaskRow.setStatus(TaskConstants.CREATED);
		MasterTasks nextTask = masterTasksRepository.findById(currentTask.getNextTaskId());
		newTaskRow.setTask(nextTask);
		newTaskRow.setBaseAttributes(currentUser);
		taskListRepository.save(currentTaskRow);
		newTaskRow = taskListRepository.save(newTaskRow);
		return newTaskRow;
	}
	
	private List<TaskList> updateTaskList(String taskName) {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		MasterTasks currentTask = masterTasksRepository.findByTaskKey(taskName);
		TaskList taskList = taskListRepository.findByTask(currentTask);
		List<TaskList> newTasks = new ArrayList<TaskList>();
		TaskList newTaskRow = new TaskList();
		taskList.setUpdateAttributes(currentUser);
		taskList.setCompleted((byte)1);
		taskList.setStatus(TaskConstants.CREATED);//status to be chnaged
//		newTaskRow.setTask(taskList.getNextTask());
		newTaskRow.setBaseAttributes(currentUser);
		newTasks.add(taskList);
		newTasks.add(newTaskRow);
		taskListRepository.save(newTasks);
		return null;
	}

	@Override
	public ResponseEntity<EmployeeSalary> getSalarySplit(SalaryDTO salaryDTO) {
		salaryDTO.setGrade(salaryDTO.getGrade());
		salaryDTO.setGrossSalary(Double.valueOf(356663));
		if(salaryDTO.getGrade()!=null && salaryDTO.getGrossSalary()!=null){
			Double minBasicSalary = 7515.20;
			EmployeeSalary empSal = new EmployeeSalary();
			salaryDTO.setGrossSalary(Double.valueOf(37663)); 
			SalaryGrade grade = salaryGradeRepository.findByGrade(salaryDTO.getGrade());
			Double minFixedSalary = grade.getFixedSalary() > salaryDTO.getGrossSalary() ? 
					salaryDTO.getGrossSalary() : grade.getFixedSalary();
			Double basicSalary = minBasicSalary < minFixedSalary * .6 ? minFixedSalary * .6 : minBasicSalary;
			Double hra = (minFixedSalary - basicSalary) > basicSalary * 0.05 ? basicSalary * 0.05 : minFixedSalary - basicSalary;
			Double medicalAllowance = minFixedSalary -(basicSalary + hra) > minFixedSalary * 0.05 ? 
					minFixedSalary * 0.05 : minFixedSalary - (basicSalary + hra);
			Long medicalAllowanceLong = medicalAllowance < 0 ? 0 : Math.round(medicalAllowance);
			Double conveyanceAllowance = minFixedSalary - (basicSalary + hra + medicalAllowanceLong);
			Long conveyanceAllowanceLong = conveyanceAllowance < 0 ? 0 : Math.round(conveyanceAllowance);
			int profTax = salaryDTO.getGrossSalary() < 1000 ? 0 : (salaryDTO.getGrossSalary() < 15000 ? 150 : 200);
			Long pfEmpContrbtn = Math.round(basicSalary < 15000 ? basicSalary * 0.6 : 15000 * 0.6);
			Long esiByEmplyr = Math.round(salaryDTO.getGrossSalary() < 15000?salaryDTO.getGrossSalary() * 0.0475 : 0);
			Long esiByEmplye = Math.round(salaryDTO.getGrossSalary() < 15000?salaryDTO.getGrossSalary() * 0.0175 : 0);
			Long leaveEncash = Math.round(basicSalary/22 * 15/12);
			Long gratuity = Math.round(basicSalary/26 * 15/12);
			Long totalDeductions = profTax + pfEmpContrbtn + esiByEmplyr + esiByEmplye + leaveEncash + gratuity ; 
			Long flexyBenKit = Math.round(salaryDTO.getGrossSalary() - minFixedSalary);
			Long grossCTC = Math.round(basicSalary + hra + medicalAllowanceLong +  conveyanceAllowanceLong + flexyBenKit ); 
			empSal.setBasicSalary(basicSalary);
			empSal.setMinFixedSalary(minFixedSalary);
			empSal.setHra(hra);
			empSal.setMedicalAllowance(medicalAllowanceLong);
			empSal.setConveyanceAllowance(conveyanceAllowanceLong);
			empSal.setProfTax(profTax);
			empSal.setEsiByEmplye(esiByEmplye);
			empSal.setEsiByEmplyr(esiByEmplyr);
			empSal.setPfEmpContrbtn(pfEmpContrbtn);
			empSal.setGratuity(gratuity);
			empSal.setLeaveEncash(leaveEncash);
			empSal.setTotalDeductions(totalDeductions);
			empSal.setFlexyBenKit(flexyBenKit);
			empSal.setGrossCTC(grossCTC);
            return new ResponseEntity<EmployeeSalary>(empSal, HttpStatus.OK);
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeSalary> saveSalaryAndOfferLetter(EmployeeSalary employeeSalary) {
		if(employeeSalary!=null){
			if(employeeSalary.getCandidate()!=null){
				User currentUser = SessionManager.getCurrentUserAsEntity();
				employeeSalary.setStatus(Constants.GENERATED);
				TaskList currentTask = createNewTaskList(TaskConstants.OFFER_LETTER_CREATION);
				employeeSalary.setCurrentTask(currentTask);
				employeeSalary.setBaseAttributes(currentUser);
				employeeSalary.setUpdateAttributes(currentUser);
				EmployeeSalary empSal = employeeSalaryRepository.saveAndFlush(employeeSalary);
				return new ResponseEntity<EmployeeSalary>(empSal, HttpStatus.OK);
			}
		}
		return null;
	}
	
	@Override
	public ResponseEntity<List<TaskList>> getCurrentUserTasks() {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		List<String> userRoles = getAllUserRoles(currentUser.getId());
		if(userRoles.isEmpty()){
			List<TaskList> taskLists = taskListRepository.findByTaskOwner(currentUser.getRoles());
			return new ResponseEntity<List<TaskList>>(taskLists, HttpStatus.OK);
		}
		return null;
	}

	private List<String> getAllUserRoles(Long id) {
		User user = userRepository.findById(id);
		List<String> userRoles = new ArrayList<String>();
		if(!user.getRoles().isEmpty()){
			user.getRoles().forEach(p->System.out.println(p.getRoleName()));
			user.getRoles().forEach(p->userRoles.add(p.getRoleName()));
		}
		return userRoles;
	}
}
