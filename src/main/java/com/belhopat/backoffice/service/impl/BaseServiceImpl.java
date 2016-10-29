package com.belhopat.backoffice.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.belhopat.backoffice.alfresco.main.AlfrescoUploadService;
import com.belhopat.backoffice.dto.AddressDTO;
import com.belhopat.backoffice.dto.EmploymentInfoDTO;
import com.belhopat.backoffice.dto.OfficialInfoDTO;
import com.belhopat.backoffice.dto.PersonalInfoDTO;
import com.belhopat.backoffice.dto.RequestObject;
import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.CandidateSequence;
import com.belhopat.backoffice.model.City;
import com.belhopat.backoffice.model.Client;
import com.belhopat.backoffice.model.ClientSequence;
import com.belhopat.backoffice.model.Consultant;
import com.belhopat.backoffice.model.ConsultantSequence;
import com.belhopat.backoffice.model.Country;
import com.belhopat.backoffice.model.Currency;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.EmployeeSalary;
import com.belhopat.backoffice.model.EmployeeSequence;
import com.belhopat.backoffice.model.LookupDetail;
import com.belhopat.backoffice.model.MasterRole;
import com.belhopat.backoffice.model.MasterTask;
import com.belhopat.backoffice.model.Reimburse;
import com.belhopat.backoffice.model.ReimburseSequence;
import com.belhopat.backoffice.model.SalaryGrade;
import com.belhopat.backoffice.model.Skill;
import com.belhopat.backoffice.model.State;
import com.belhopat.backoffice.model.Task;
import com.belhopat.backoffice.model.TimeZone;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.CandidateRepository;
import com.belhopat.backoffice.repository.CandidateSequenceRepository;
import com.belhopat.backoffice.repository.CityRepository;
import com.belhopat.backoffice.repository.ClientRepository;
import com.belhopat.backoffice.repository.ClientSequenceRepository;
import com.belhopat.backoffice.repository.ConsultantSequenceRepository;
import com.belhopat.backoffice.repository.CountryRepository;
import com.belhopat.backoffice.repository.CurrencyRepository;
import com.belhopat.backoffice.repository.EmployeeRepository;
import com.belhopat.backoffice.repository.EmployeeSalaryRepository;
import com.belhopat.backoffice.repository.EmployeeSequenceRepository;
import com.belhopat.backoffice.repository.LookupDetailRepository;
import com.belhopat.backoffice.repository.MasterTaskRepository;
import com.belhopat.backoffice.repository.ReimburseSequenceRepository;
import com.belhopat.backoffice.repository.SalaryGradeRepository;
import com.belhopat.backoffice.repository.SkillRepository;
import com.belhopat.backoffice.repository.StateRepository;
import com.belhopat.backoffice.repository.TaskRepository;
import com.belhopat.backoffice.repository.TimeZoneRepository;
import com.belhopat.backoffice.repository.UserRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.PDFService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.Constants;
import com.belhopat.backoffice.util.DateUtil;
import com.belhopat.backoffice.util.TaskConstants;
import com.itextpdf.text.DocumentException;

/**
 * @author BHP_DEV service implementation for general functionalities
 */
@Component
public class BaseServiceImpl implements BaseService {

	@Autowired
	LookupDetailRepository lookupDetailRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	CurrencyRepository currencyRepository;

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
	ReimburseSequenceRepository reimburseSequenceRepository;
	
	@Autowired
	ConsultantSequenceRepository consultantSequenceRepository;

	@Autowired
	MasterTaskRepository masterTaskRepository;

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	SalaryGradeRepository salaryGradeRepository;

	@Autowired
	EmployeeSalaryRepository employeeSalaryRepository;

	@Autowired
	AlfrescoUploadService alfrescoUploadService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PDFService pdfService;

	@Autowired
	TimeZoneRepository timezoneRepository;

	@Autowired
	ClientRepository clientRepository;

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
		List<LookupDetail> gender = lookupDetailRepository.findByLookupKey(Constants.GENDER);
		List<ResponseObject> clients = clientRepository.getClientDropdownData();
		List<ResponseObject> employees = employeeRepository.getEmployeesDropDown();
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
		dropDownMap.put(Constants.GENDER, gender);
		dropDownMap.put(Constants.CLIENTS, clients);
		dropDownMap.put(Constants.EMPLOYEES, employees);
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
		List<TimeZone> timezones = timezoneRepository.findAll();
		Map<String, List<?>> dropDownMap = new HashMap<>();
		dropDownMap.put(Constants.DIVISION, divisions);
		dropDownMap.put(Constants.DESIGNATION, designations);
		dropDownMap.put(Constants.PURPOSE, purposes);
		dropDownMap.put(Constants.BLOOD_GROUP, bloodGroups);
		dropDownMap.put(Constants.EMPLOYMENT_STATUS, employmentStatuses);
		dropDownMap.put(Constants.FAMILY_MEMBER, familyMembers);
		dropDownMap.put(Constants.SKILL, skills);
		dropDownMap.put(Constants.COUNTRY, countries);
		dropDownMap.put(Constants.TIMEZONE, timezones);
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
		List<State> states = stateRepository.findByCountryId(countryId);
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
	public <T> Long getSequenceIncrement( Class<T> clazz ) {
		Long increment = null;
		if( clazz.equals( Candidate.class )) {
			CandidateSequence candidateSequence = candidateSequenceRepository.save( new CandidateSequence() );
			increment = candidateSequence.getId();
		} else if( clazz.equals( Employee.class )) {
			EmployeeSequence employeeSequence = employeeSequenceRepository.save( new EmployeeSequence() );
			increment = employeeSequence.getId();
		} else if( clazz.equals( Client.class )) {
			ClientSequence clientSequence = clientSequenceRepository.save( new ClientSequence() );
			increment = clientSequence.getId();
		} else if( clazz.equals( Reimburse.class )) {
			ReimburseSequence reimburseSequence = reimburseSequenceRepository.save( new ReimburseSequence() );
			increment = reimburseSequence.getId();
		}
		else if( clazz.equals( Consultant.class )) {
			ConsultantSequence consultantSequence = consultantSequenceRepository.save( new ConsultantSequence() );
			increment = consultantSequence.getId();
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
		dropDownMap.put(Constants.EMP_DESIG_BDM, employeeRepository.findByDesignation(Constants.EMP_DESIG_CEO));
		dropDownMap.put(Constants.CLIENT_STATUS, lookupDetailRepository.findByLookupKey(Constants.CLIENT_STATUS));
		dropDownMap.put(Constants.COUNTRY, countryRepository.findAll());
		dropDownMap.put(Constants.TIMEZONE, timezoneRepository.findAll());
		dropDownMap.put(Constants.DESIGNATION, lookupDetailRepository.findByLookupKey(Constants.DESIGNATION));
		dropDownMap.put(Constants.DIVISION, lookupDetailRepository.findByLookupKey(Constants.DIVISION));
		return dropDownMap;
	}

	@Override
	public ResponseEntity<List<Task>> createOfferLetter(RequestObject requestObject)
			throws MalformedURLException, DocumentException, IOException, ParseException {
		EmployeeSalary employeeSalary = new EmployeeSalary();
		pdfService.generateOfferLetterPDF(employeeSalary);
		return null;
	}

	@Override
	public Task createNewTaskList(String taskName) {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		Task currentTaskRow = new Task();
		MasterTask currentTask = masterTaskRepository.findByTaskKey(taskName);
		currentTaskRow.setBaseAttributes(currentUser);
		currentTaskRow.setCompleted(true);
		currentTaskRow.setMasterTask(currentTask);
		currentTaskRow.setStatus(TaskConstants.CREATED);
		MasterTask nextTask = masterTaskRepository.findById(currentTask.getNextTaskId());
		taskRepository.save(currentTaskRow);
		if (nextTask != null) {
			Task newTaskRow = new Task();
			newTaskRow.setMasterTask(nextTask);
			newTaskRow.setBaseAttributes(currentUser);
			newTaskRow = taskRepository.save(newTaskRow);
			return newTaskRow;
		}
		return currentTaskRow;
	}

	@Override
	public List<Task> updateTaskList(String taskName) {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		MasterTask currentTask = masterTaskRepository.findByTaskKey(taskName);
		Task taskList = taskRepository.findByMasterTask(currentTask);
		List<Task> newTasks = new ArrayList<Task>();
		Task newTaskRow = new Task();
		taskList.setUpdateAttributes(currentUser);
		taskList.setCompleted(true);
		taskList.setStatus(TaskConstants.CREATED);// status to be chnaged
		// newTaskRow.setTask(taskList.getNextTask());
		newTaskRow.setBaseAttributes(currentUser);
		newTasks.add(taskList);
		newTasks.add(newTaskRow);
		taskRepository.save(newTasks);
		return null;
	}

	@Override
	public ResponseEntity<EmployeeSalary> getSalarySplit(Double grossSalary, String grade) {
		if (grade != null && grossSalary != null) {
			Double minBasicSalary = 7515.20;
			EmployeeSalary empSal = new EmployeeSalary();
			SalaryGrade gradeEntity = salaryGradeRepository.findByGrade(grade);
			Double minFixedSalary = gradeEntity.getFixedSalary() > grossSalary ? grossSalary
					: gradeEntity.getFixedSalary();
			Double basicSalary = minBasicSalary < minFixedSalary * .6 ? minFixedSalary * .6 : minBasicSalary;
			Double hra = (minFixedSalary - basicSalary) > basicSalary * 0.05 ? basicSalary * 0.05
					: minFixedSalary - basicSalary;
			Double medicalAllowance = minFixedSalary - (basicSalary + hra) > minFixedSalary * 0.05
					? minFixedSalary * 0.05 : minFixedSalary - (basicSalary + hra);
			Long medicalAllowanceLong = medicalAllowance < 0 ? 0 : Math.round(medicalAllowance);
			Double conveyanceAllowance = minFixedSalary - (basicSalary + hra + medicalAllowanceLong);
			Long conveyanceAllowanceLong = conveyanceAllowance < 0 ? 0 : Math.round(conveyanceAllowance);
			Long profTax = grossSalary < 1000 ? 0L : (grossSalary < 15000 ? 150L : 200L);
			Long pfEmpContrbtn = Math.round(basicSalary < 15000 ? basicSalary * 0.12 : 15000 * 0.12);
			Long esiByEmplyr = Math.round(grossSalary < 15000 ? grossSalary * 0.0475 : 0);
			Long esiByEmplye = Math.round(grossSalary < 15000 ? grossSalary * 0.0175 : 0);
			Long leaveEncash = Math.round(basicSalary / 22 * 15 / 12);
			Long gratuity = Math.round(basicSalary / 26 * 15 / 12);
			Long totalDeductions = profTax + pfEmpContrbtn + pfEmpContrbtn + esiByEmplyr + esiByEmplye + leaveEncash
					+ gratuity;
			Long flexyBenKit = Math.round(grossSalary - minFixedSalary);
			Long grossCTC = Math.round(grossSalary + pfEmpContrbtn + esiByEmplyr + leaveEncash + gratuity);
			Long netTakeHomeBeforeTDS = grossCTC - totalDeductions;
			empSal.setGrossSalary(grossSalary);
			empSal.setBasicSalary(basicSalary);
			empSal.setMinFixedSalary(minFixedSalary);
			empSal.setHra(hra);
			empSal.setMedicalAllowance(medicalAllowanceLong);
			empSal.setConveyanceAllowance(conveyanceAllowanceLong);
			empSal.setProfTax(profTax);
			empSal.setEsiByEmplye(esiByEmplye);
			empSal.setEsiByEmplyr(esiByEmplyr);
			empSal.setPfEmpContrbtn(pfEmpContrbtn);
			empSal.setPfCompContrbtn(pfEmpContrbtn);
			empSal.setGratuity(gratuity);
			empSal.setLeaveEncash(leaveEncash);
			empSal.setTotalDeductions(totalDeductions);
			empSal.setFlexyBenKit(flexyBenKit);
			empSal.setGrossCTC(grossCTC);
			empSal.setNetTakeHomeBeforeTDS(netTakeHomeBeforeTDS);
			return new ResponseEntity<EmployeeSalary>(empSal, HttpStatus.OK);
		}
		return null;
	}

	@Override
	public ResponseEntity<EmployeeSalary> saveSalaryAndOfferLetter(EmployeeSalary employeeSalary)
			throws MalformedURLException, DocumentException, IOException, ParseException {
		if (employeeSalary != null) {
			if (employeeSalary.getCandidate() != null) {
				Candidate candidate = candidateRepository.findById(employeeSalary.getCandidate().getId());
				User currentUser = SessionManager.getCurrentUserAsEntity();
				employeeSalary.setStatus(Constants.GENERATED);
				employeeSalary.setBaseAttributes(currentUser);
				employeeSalary.setUpdateAttributes(currentUser);
				// byte[] offerLetter =
				pdfService.generateOfferLetterPDF(employeeSalary);
				// String document =
				// alfrescoUploadService.uploadFileByCategory(offerLetter,employeeSalary,Constants.OFFER_LETTERS);
				employeeSalary
						.setOfferLetterFileName(candidate.getCandidateId() + "_" + candidate.getFirstName() + ".pdf");
				candidate.setOfferletterStatus(true);
				candidateRepository.saveAndFlush(candidate);
				EmployeeSalary empSal = employeeSalaryRepository.saveAndFlush(employeeSalary);
				return new ResponseEntity<EmployeeSalary>(empSal, HttpStatus.OK);
			}
		}
		return null;
	}

	@Override
	public ResponseEntity<List<Task>> getCurrentUserTasks() {
		User currentUser = SessionManager.getCurrentUserAsEntity();
		List<String> userRoles = getAllUserRoles(currentUser.getId());
		if (!userRoles.isEmpty()) {
			List<Task> taskLists = taskRepository.findByTaskOwner(userRoles);
			return new ResponseEntity<List<Task>>(taskLists, HttpStatus.OK);
		}
		return null;
	}

	private List<String> getAllUserRoles(Long id) {
		User user = userRepository.findById(id);
		List<String> userRoles = new ArrayList<String>();
		if (!user.getRoles().isEmpty()) {
			for (MasterRole role : user.getRoles()) {
				userRoles.add(role.getRoleName());
			}
			// user.getRoles().forEach(p->userRoles.add(p.getRoleName()));
		}
		return userRoles;
	}

	@Override
	public void getFileByNameAndCategory(Long empSalId, HttpServletResponse response) throws IOException {
		EmployeeSalary empSal = employeeSalaryRepository.findById(empSalId);
		byte[] fileBytes = alfrescoUploadService.getBytesByNameAndCategory(Constants.OFFER_LETTERS,
				empSal.getOfferLetterFileName());
		generateDownloadLink(fileBytes, empSal.getOfferLetterFileName(), response);
	}

	@Override
	public void previewOfferLetter(Long empSalId, HttpServletResponse response)
			throws IOException, DocumentException, ParseException {
		EmployeeSalary empSal = employeeSalaryRepository.findById(empSalId);
		byte[] offerLetter = pdfService.generateOfferLetterPDF(empSal);
		generateDownloadLink(offerLetter, empSal.getOfferLetterFileName(), response);
	}

	public void generateDownloadLink(byte[] fileBytes, String fileName, HttpServletResponse response)
			throws IOException {
		OutputStream output = response.getOutputStream();
		response.setContentType(Constants.PDF_CONTENT_TYPE);
		response.addHeader(Constants.CONTENT_DISPOSITION, Constants.ATTACHMENT + fileName);
		output.write(fileBytes);
		output.flush();
		response.flushBuffer();
		output.close();
	}

	@Override
	public DataTablesOutput<Task> getUserTasks(DataTablesInput input) {
		return taskRepository.findAll(input);
	}

	@Override
	public PersonalInfoDTO getPersonalInfo(Candidate candidate) throws ParseException {
		PersonalInfoDTO personalInfo = new PersonalInfoDTO();
		AddressDTO permenantAddress = new AddressDTO();
		permenantAddress.setAddress1(candidate.getPermanentAddress().getAddress1());
		permenantAddress.setAddress2(candidate.getPermanentAddress().getAddress2());
		permenantAddress.setCity(candidate.getPermanentAddress().getCity().getCode());
		permenantAddress.setState(candidate.getPermanentAddress().getCity().getState().getCode());
		permenantAddress.setCountry(candidate.getPermanentAddress().getCity().getState().getCountry().getCode());
		AddressDTO currentAddress = new AddressDTO();
		currentAddress.setAddress1(candidate.getCurrentAddress().getAddress1());
		currentAddress.setAddress2(candidate.getCurrentAddress().getAddress2());
		currentAddress.setCity(candidate.getCurrentAddress().getCity().getCode());
		currentAddress.setState(candidate.getCurrentAddress().getCity().getState().getCode());
		currentAddress.setCountry(candidate.getCurrentAddress().getCity().getState().getCountry().getCode());
		personalInfo.setFirstName(candidate.getFirstName());
		personalInfo.setMiddleName(candidate.getMiddleName());
		personalInfo.setLastName(candidate.getLastName());
		personalInfo.setDob(DateUtil.toDdMmYyyy(candidate.getDob()));
		personalInfo.setGender(candidate.getGender().getCode());
		personalInfo.setBloodGroup(candidate.getBloodGroup().getCode());
		personalInfo.setCountryOfOrigin(candidate.getCountryOfOrigin().getDescription());
		personalInfo.setPersonalContactNo(candidate.getPersonalContactNo().getNumber());
		personalInfo.setPersonalEmailId(candidate.getPersonalEmail());
		personalInfo.setOfficialContactNo(candidate.getOfficialContactNo().getNumber());
		personalInfo.setOfficialEmailId(candidate.getOfficialEmail());
		personalInfo.setOfficialContactNo(candidate.getOfficialContactNo().getNumber());
		personalInfo.setPermenantAddress(permenantAddress);
		personalInfo.setCurrentAddress(currentAddress);
		return personalInfo;
	}

	@Override
	public EmploymentInfoDTO getEmploymentInfo(Candidate candidate) throws ParseException {
		EmploymentInfoDTO employmentInfo = new EmploymentInfoDTO();
		String experience = Integer.toString(candidate.getPriorExperienceYear()) + " years ,"
				+ Integer.toString(candidate.getPriorExperienceYear()) + "Months";
		AddressDTO onsiteAddress = new AddressDTO();
		onsiteAddress.setAddress1(candidate.getOnsiteAddress().getAddress1());
		onsiteAddress.setAddress2(candidate.getOnsiteAddress().getAddress2());
		onsiteAddress.setCity(candidate.getOnsiteAddress().getCity().getCode());
		onsiteAddress.setState(candidate.getOnsiteAddress().getCity().getState().getCode());
		onsiteAddress.setCountry(candidate.getOnsiteAddress().getCity().getState().getCountry().getCode());
		employmentInfo.setExperience(experience);
		employmentInfo.setDoj(DateUtil.toDdMmYyyy(candidate.getDoj()));
		employmentInfo.setDivision(candidate.getDivision().getDescription());
		employmentInfo.setDesignation(candidate.getDesignation().getDescription());
		employmentInfo.setEmploymentStatus(candidate.getEmploymentStatus().getDescription());
		employmentInfo.setPurpose(candidate.getPurpose().getDescription());
		employmentInfo.setClient(candidate.getClient().getClientId());
		employmentInfo.setPartner(candidate.getPartner());
		employmentInfo.setSourcedBy(candidate.getSourcedBy());
		employmentInfo.setOnsiteAddress(onsiteAddress);
		// TODO set skill set as a string :)
		return employmentInfo;
	}

	@Override
	public OfficialInfoDTO getOfficialInfo(Candidate candidate) throws ParseException {
		OfficialInfoDTO officialInfo = new OfficialInfoDTO();
		AddressDTO bankAddress = new AddressDTO();
		bankAddress.setAddress1(candidate.getBankAccount().getBankAddress().getAddress1());
		bankAddress.setAddress2(candidate.getBankAccount().getBankAddress().getAddress2());
		bankAddress.setCity(candidate.getBankAccount().getBankAddress().getCity().getCode());
		bankAddress.setState(candidate.getBankAccount().getBankAddress().getCity().getState().getCode());
		bankAddress.setCountry(candidate.getBankAccount().getBankAddress().getCity().getState().getCountry().getCode());
		officialInfo.setBankAccountNo(candidate.getBankAccount().getAccountNo());
		officialInfo.setBankIFSCCode(candidate.getBankAccount().getIFSCCode());
		officialInfo.setBankAccountHolderName(candidate.getBankAccount().getAccountHolderName());
		officialInfo.setBankAccountHolderName(candidate.getBankAccount().getAccountHolderName());
		officialInfo.setBankName(candidate.getBankAccount().getBankName());
		officialInfo.setBankAddress(bankAddress);
		officialInfo.setPassportNo(candidate.getPassport().getPassportNo());
		officialInfo.setPassportIssueDate(DateUtil.toDdMmYyyy(candidate.getPassport().getIssueDate()));
		officialInfo.setPassportExpiryDate(DateUtil.toDdMmYyyy(candidate.getPassport().getExpiryDate()));
		officialInfo.setPassportCountry(candidate.getPassport().getCountry().getDescription());
		officialInfo.setDrivingLicenceNo(candidate.getOfficialDetails().getDrivingLicenceNo());
		officialInfo.setPANNo(candidate.getOfficialDetails().getPANNo());
		officialInfo.setESINo(candidate.getOfficialDetails().getESINo());
		officialInfo.setPFNo(candidate.getOfficialDetails().getPFNo());
		officialInfo.setForexCardNo(candidate.getOfficialDetails().getForexCardNo());
		officialInfo.setForexCardAgency(candidate.getOfficialDetails().getForexCardAgency());
		return officialInfo;
	}

	@Override
	public ResponseEntity<List<Currency>> getCurrencies() {
		List<Currency> currencies = currencyRepository.findAll();
		return new ResponseEntity<List<Currency>>(currencies, HttpStatus.OK);
	}

	@Override
	public Map<String, Long> getEmployeeIdAndCodeMap() {
		Map<String, Long> map = new HashMap<>();
		// List<ResponseObject> idAndCodeList =
		// employeeRepository.findAllIdsAndCodes();
		// for (ResponseObject object : idAndCodeList) {
		// map.put("C0001", 1L);
		// }
		return map;
	}

	@Override
	public User getCurrentUser() {
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		User currentUser = userRepository.findById(loggedInUser.getId());
		return currentUser;
	}

	@Override
	public void saveImageIntoUser() throws IOException {
		File imgPath = new File("/home/sujith/Desktop/rafique.jpg");
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.setUserImage(data.getData());
		}
		userRepository.save(users);
	}

}
