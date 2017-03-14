package com.belhopat.backoffice.service.impl;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
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

import com.belhopat.backoffice.dto.CandidateViewDTO;
import com.belhopat.backoffice.dto.EmployeeDto;
import com.belhopat.backoffice.dto.EmployeeViewDTO;
import com.belhopat.backoffice.dto.EmploymentInfoDTO;
import com.belhopat.backoffice.dto.OfficialInfoDTO;
import com.belhopat.backoffice.dto.PersonalInfoDTO;
import com.belhopat.backoffice.dto.UploadDTO;
import com.belhopat.backoffice.dto.UploadResponse;
import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.Country;
import com.belhopat.backoffice.model.Employee;
import com.belhopat.backoffice.model.LookupDetail;
import com.belhopat.backoffice.model.S3BucketFile;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.CandidateRepository;
import com.belhopat.backoffice.repository.CountryRepository;
import com.belhopat.backoffice.repository.EmployeeRepository;
import com.belhopat.backoffice.repository.LookupDetailRepository;
import com.belhopat.backoffice.repository.S3BucketFileRepository;
import com.belhopat.backoffice.repository.TimeZoneRepository;
import com.belhopat.backoffice.repository.UserRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.EmployeeService;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.service.S3BucketCoreService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.Constants;
import com.belhopat.backoffice.util.TaskConstants;
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
	CountryRepository countryRepository;

	@Autowired
	TimeZoneRepository timeZoneRepository;

	@Autowired
	LookupDetailRepository lookupDetailRepository;

	@Autowired
	MailService mailService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	S3BucketFileRepository s3BucketFileRepository;

	@Autowired
	S3BucketCoreService s3BucketCoreService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.EmployeeService#saveOrUpdateEmployee(com.
	 * belhopat.backoffice.model.Employee) saves the employee to the db
	 */
	@Override
	public ResponseEntity<Map<String, String>> saveOrUpdateEmployee(EmployeeDto employeeDto) throws MessagingException {
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		Employee hrr = employeeRepository.findOne(employeeDto.getHrRecruiter());
		Employee reportingMngr = employeeRepository.findOne(employeeDto.getReportingManager());
		Employee hrManager = employeeRepository.findOne(employeeDto.getHrManager());
		Employee accountManager = employeeRepository.findOne(employeeDto.getAccountManager());
		LookupDetail businessUnit = lookupDetailRepository.findOne(employeeDto.getBusinessUnit());
		Candidate employeeMaster = candidateRepository.findOne(employeeDto.getEmployeeMasterId());
		Country workLocation = countryRepository.findOne(employeeDto.getWorkLocation());
		Country baseLocation = countryRepository.findOne(employeeDto.getBaseLocation());
		Employee employee = null;
		if (employeeDto.getId() == null) {
			employee = new Employee();
			employee.setBaseAttributes(loggedInUser);
			Long increment = baseService.getSequenceIncrement(employee.getClass());
			String employeeId = SequenceGenerator.generateEmployeeId(increment, employeeMaster.getDivision().getCode());
			employee.setEmployeeId(employeeId);
		} else {
			employee = employeeRepository.findOne(employeeDto.getId());
			employee.setUpdateAttributes(loggedInUser);
		}
		employee.setWorkLocation(workLocation);
		employee.setHrRecruiter(hrr);
		employee.setReportingManager(reportingMngr);
		employee.setBaseLocation(baseLocation);
		employee.setAccountManager(accountManager);
		employee.setBusinessUnit(businessUnit);
		employee.setEmployeeMaster(employeeMaster);
		employee.setHrManager(hrManager);
		if (employeeDto.getBelhopatDesignation() != null)
			employee.setBelhopatDesignation(employeeDto.getBelhopatDesignation());
		employee.setJoiningDate(employeeDto.getJoiningDate());
		employee = employeeRepository.save(employee);
		baseService.createNewTaskList(TaskConstants.CREATE_OFFICIAL_EMAIL_ID, employee.getId());
		if (employee != null) {
			Map<String, String> responseMap = new HashMap<>();
			Candidate candidate = candidateRepository.findById(employeeDto.getEmployeeMasterId());
			candidate.setEmployee(true);
			candidate = candidateRepository.save(candidate);
			if (candidate != null) {
				String employeeName = employee.getEmployeeMaster().getFirstName() + " "
						+ employee.getEmployeeMaster().getLastName();
				responseMap.put("Message", employeeName);
				try {
					mailService.sendEmployeeRegMail(employee);
					mailService.sendCreateOfficialEmail(employee);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Map<String, String>>(HttpStatus.NO_CONTENT);
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
	public EmployeeDto getAnEmployee(Long id) {
		Employee employeeObj = employeeRepository.findById(id);
		EmployeeDto outputObj = new EmployeeDto();
		outputObj.setId(employeeObj.getId());
		outputObj.setEmployeeId(employeeObj.getEmployeeId());
		outputObj.setAccountManager(
				employeeObj.getAccountManager() != null ? employeeObj.getAccountManager().getId() : -999);
		outputObj.setHrManager(employeeObj.getHrManager() != null ? employeeObj.getHrManager().getId() : -999);
		outputObj.setHrRecruiter(employeeObj.getHrRecruiter() != null ? employeeObj.getHrRecruiter().getId() : -999);
		outputObj.setReportingManager(
				employeeObj.getReportingManager() != null ? employeeObj.getReportingManager().getId() : -999);
		outputObj.setEmployeeMasterId(
				employeeObj.getEmployeeMaster() != null ? employeeObj.getEmployeeMaster().getId() : -999);
		outputObj.setBaseLocation(employeeObj.getBaseLocation() != null ? employeeObj.getBaseLocation().getId() : -999);
		outputObj.setWorkLocation(employeeObj.getWorkLocation() != null ? employeeObj.getWorkLocation().getId() : -999);
		outputObj.setBusinessUnit(employeeObj.getBusinessUnit() != null ? employeeObj.getBusinessUnit().getId() : -999);
		outputObj.setJoiningDate(employeeObj.getJoiningDate());
		outputObj.setMail(employeeObj.getEmployeeMaster().getPersonalEmail() != null
				? employeeObj.getEmployeeMaster().getPersonalEmail() : "-999");
		return outputObj;
	}

	@Override
	public ResponseEntity<EmployeeViewDTO> getEmployeeView(Long empId) throws ParseException {
		EmployeeViewDTO employeeView = getEmployeeViewDTO(empId);
		if (employeeView != null) {

			return new ResponseEntity<EmployeeViewDTO>(employeeView, HttpStatus.OK);
		}
		return new ResponseEntity<EmployeeViewDTO>(HttpStatus.NO_CONTENT);
	}

	private EmployeeViewDTO getEmployeeViewDTO(Long empId) throws ParseException {
		Employee employee = employeeRepository.findById(empId);
		Employee hrr = employeeRepository.findOne(employee.getHrRecruiter().getId());
		Employee reportingMngr = employeeRepository.findOne(employee.getReportingManager().getId());
		Employee hrManager = employeeRepository.findOne(employee.getHrManager().getId());
		Employee accountManager = employeeRepository.findOne(employee.getAccountManager().getId());
		// LookupDetail businessUnit =
		// lookupDetailRepository.findOne(employee.getBusinessUnit().getId());
		// if (employee == null) {
		// return null;
		// }
		EmployeeViewDTO employeeView = new EmployeeViewDTO();
		employeeView.setEmployeeId(employeeView.getEmployeeId());
		employeeView.setAccountManager(accountManager.getEmployeeId());
		employeeView.setReportingManager(reportingMngr.getEmployeeId());
		employeeView.setHrManager(hrManager.getEmployeeId());
		employeeView.setHrRecruiter(hrr.getEmployeeId());
		employeeView.setBaseLocation(employee.getBaseLocation().getDescription());
		employeeView.setWorkLocation(employee.getWorkLocation().getDescription());
		employeeView.setBusinessUnit(employee.getBusinessUnit().getDescription());
		CandidateViewDTO candidateView = new CandidateViewDTO();
		PersonalInfoDTO personalInfo = baseService.getPersonalInfo(employee.getEmployeeMaster());
		EmploymentInfoDTO employmentInfo = baseService.getEmploymentInfo(employee.getEmployeeMaster());
		OfficialInfoDTO officialInfo = baseService.getOfficialInfo(employee.getEmployeeMaster());
		// FamilyInfoDTO familyInfo = getFamilyInfo(candidate);
		candidateView.setEmployment(employmentInfo);
		candidateView.setPersonal(personalInfo);
		candidateView.setOfficial(officialInfo);
		// TODO candidateView.setFamily(familyInfo);
		return employeeView;
	}

	@Override
	public ResponseEntity<Employee> getloggedInEmployee() {
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		User user = userRepository.findById(loggedInUser.getId());
		Employee loggedInEmployee = employeeRepository.findById(user.getEmployeeId());
		if (loggedInEmployee != null) {
			return new ResponseEntity<Employee>(loggedInEmployee, HttpStatus.OK);
		}
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

	@Override
	public UploadResponse uploadFile(UploadDTO uploadDTO) throws Exception {
		UploadResponse response = baseService.getErrorResponse();
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		Employee employee = employeeRepository.findById(uploadDTO.getUserId());
		if (uploadDTO.getFile() == null || uploadDTO.getFile().isEmpty()) {
			return response;
		}
		S3BucketFile s3BucketFile = new S3BucketFile();
		s3BucketFile.setBaseAttributes(loggedInUser);
		s3BucketFile.setBucketName(Constants.BUCKET_NAME);
		s3BucketFile.setUserId(employee.getEmployeeId());
		s3BucketFile.setFileType(uploadDTO.getType());
		String contentType = uploadDTO.getFile().getOriginalFilename().split("\\.")[1];
		s3BucketFile.setContentType(contentType);
		s3BucketFile.setFileName(uploadDTO.getFile().getOriginalFilename());
		ByteArrayInputStream byteContent = new ByteArrayInputStream(uploadDTO.getFile().getBytes());
		boolean status = s3BucketCoreService.uploadFile(s3BucketFile, byteContent);
		if (!status) {
			return response;
		}
		s3BucketFileRepository.save(s3BucketFile);
		response = baseService.getSuccessResponse();
		return response;
	}

	@Override
	public List<S3BucketFile> getFiles(Long employeeId) throws Exception {
		List<S3BucketFile> files = new ArrayList<>();
		Employee employee = employeeRepository.findById(employeeId);
		files = s3BucketFileRepository.findByUserId(employee.getEmployeeId());
		return files;
	}

}
