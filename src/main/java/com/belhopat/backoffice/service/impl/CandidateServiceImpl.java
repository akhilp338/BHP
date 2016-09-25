package com.belhopat.backoffice.service.impl;

import java.text.ParseException;
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

import com.belhopat.backoffice.dto.AddressDTO;
import com.belhopat.backoffice.dto.CandidateViewDTO;
import com.belhopat.backoffice.dto.EmploymentInfoDTO;
import com.belhopat.backoffice.dto.OfficialInfoDTO;
import com.belhopat.backoffice.dto.PersonalInfoDTO;
import com.belhopat.backoffice.dto.ResponseObject;
import com.belhopat.backoffice.model.BankAccount;
import com.belhopat.backoffice.model.Candidate;
import com.belhopat.backoffice.model.EmployeeSalary;
import com.belhopat.backoffice.model.SalaryGrade;
import com.belhopat.backoffice.model.Task;
import com.belhopat.backoffice.model.User;
import com.belhopat.backoffice.repository.CandidateRepository;
import com.belhopat.backoffice.repository.EmployeeSalaryRepository;
import com.belhopat.backoffice.repository.SalaryGradeRepository;
import com.belhopat.backoffice.service.BaseService;
import com.belhopat.backoffice.service.CandidateService;
import com.belhopat.backoffice.service.MailService;
import com.belhopat.backoffice.session.SessionManager;
import com.belhopat.backoffice.util.DateUtil;
import com.belhopat.backoffice.util.TaskConstants;
import com.belhopat.backoffice.util.sequence.SequenceGenerator;

/**
 * @author BHP_DEV Service layer to implement candidates business
 */
@Component
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	BaseService baseService;

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	SalaryGradeRepository salaryGradeRepository;

	@Autowired
	EmployeeSalaryRepository employeeSalaryRepository;

	@Autowired
	MailService mailService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.belhopat.backoffice.service.CandidateService#getCandidates(org.
	 * springframework.data.jpa.datatables.mapping.DataTablesInput) gets the
	 * candidates with appropriate specifications
	 * 
	 */
	@Override
	public DataTablesOutput<Candidate> getCandidates(DataTablesInput input, boolean employee) {

		Specification<Candidate> specification = new Specification<Candidate>() {
			@Override
			public Predicate toPredicate(Root<Candidate> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isNotDeleted = criteriaBuilder.equal(root.get("deleted"), false);
				// TODO remove employment status from candidate and add in
				// employee
				if (employee) {
					Predicate employeeStatus = criteriaBuilder.equal(root.get("employee"), false);
					return criteriaBuilder.and(isNotDeleted, employeeStatus);

				}
				return criteriaBuilder.and(isNotDeleted);
			}
		};
		DataTablesOutput<Candidate> dataTablesOutput = candidateRepository.findAll(input, specification);
		return dataTablesOutput;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.CandidateService#getCandidate(java.lang.
	 * Long) gets a single candidate with its id
	 */
	@Override
	public ResponseEntity<Candidate> getCandidate(Long candidateId) {
		Candidate candidate = candidateRepository.findById(candidateId);
		if (candidate != null) {
			return new ResponseEntity<Candidate>(candidate, HttpStatus.OK);
		}
		return new ResponseEntity<Candidate>(HttpStatus.NO_CONTENT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.CandidateService#getCandidateView(java.
	 * lang. Long) gets a single candidate details with its id
	 */
	@Override
	public ResponseEntity<CandidateViewDTO> getCandidateView(Long candidateId) throws ParseException {
		CandidateViewDTO candidateView = getcandidateView(candidateId);
		if (candidateView != null) {

			return new ResponseEntity<CandidateViewDTO>(candidateView, HttpStatus.OK);
		}
		return new ResponseEntity<CandidateViewDTO>(HttpStatus.NO_CONTENT);
	}

	private CandidateViewDTO getcandidateView(Long candidateId) throws ParseException {
		Candidate candidate = candidateRepository.findById(candidateId);
		if (candidate == null) {
			return null;
		}
		CandidateViewDTO candidateView = new CandidateViewDTO();
		PersonalInfoDTO personalInfo = getPersonalInfo(candidate);
		EmploymentInfoDTO employmentInfo = getEmploymentInfo(candidate);
		OfficialInfoDTO officialInfo = getOfficialInfo(candidate);
		// FamilyInfoDTO familyInfo = getFamilyInfo(candidate);
		candidateView.setEmployment(employmentInfo);
		candidateView.setPersonal(personalInfo);
		candidateView.setOfficial(officialInfo);
		// TODO candidateView.setFamily(familyInfo);
		return candidateView;
	}

	private PersonalInfoDTO getPersonalInfo(Candidate candidate) throws ParseException {
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

	private EmploymentInfoDTO getEmploymentInfo(Candidate candidate) throws ParseException {
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

	private OfficialInfoDTO getOfficialInfo(Candidate candidate) throws ParseException {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.CandidateService#saveOrUpdateCandidate(
	 * com.belhopat.backoffice.model.Candidate) saves or updates the candidate
	 * to the database
	 */
	@Override
	public ResponseEntity<Map<String, String>> saveOrUpdateCandidate(Candidate candidateObj) {
		Map<String, String> responseMap = new HashMap<>();
		Candidate candidate = null;
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		if (candidateObj.getId() == null) {
			candidate = addCandidate(loggedInUser, candidateObj);
		} else {
			candidate = updateCandidate(loggedInUser, candidateObj);
		}
		if (candidate != null) {
			responseMap.put("Message", candidate.getFirstName() + " " + candidate.getLastName());
			return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.OK);
		}
		return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.NO_CONTENT);
	}

	/**
	 * @param loggedInUser
	 * @param candidateObj
	 * @return Candidate sets the dto to entity
	 */
	private Candidate updateCandidate(User loggedInUser, Candidate candidateObj) {
		Candidate newCandidate = candidateRepository.findById(candidateObj.getId());
		newCandidate = getUpdatedWithBaseAttributes(candidateObj, newCandidate);
		if (candidateObj.getBankAccount() != null) {
			newCandidate = setBankAccountInDetail(candidateObj.getBankAccount(), newCandidate);
		}
		if (candidateObj.getBloodGroup() != null) {
			newCandidate.setBloodGroup(candidateObj.getBloodGroup());
		}
		if (candidateObj.getCountryOfOrigin() != null) {
			newCandidate.setCountryOfOrigin(candidateObj.getCountryOfOrigin());
		}
		if (candidateObj.getCountryToVisit() != null) {
			newCandidate.setCountryToVisit(candidateObj.getCountryToVisit());
		}
		if (candidateObj.getCurrentAddress() != null) {
			newCandidate.setCurrentAddress(candidateObj.getCurrentAddress());
		}
		if (candidateObj.getDesignation() != null) {
			newCandidate.setDesignation(candidateObj.getDesignation());
		}
		if (candidateObj.getDivision() != null) {
			newCandidate.setDivision(candidateObj.getDivision());
		}
		if (candidateObj.getEmploymentStatus() != null) {
			newCandidate.setEmploymentStatus(candidateObj.getEmploymentStatus());
		}
		if (candidateObj.getOfficialDetails() != null) {
			newCandidate.setOfficialDetails(candidateObj.getOfficialDetails());
		}
		if (candidateObj.getOnsiteAddress() != null) {
			newCandidate.setOnsiteAddress(candidateObj.getOnsiteAddress());
		}
		if (candidateObj.getPassport() != null) {
			newCandidate.setPassport(candidateObj.getPassport());
		}
		if (candidateObj.getPermanentAddress() != null) {
			newCandidate.setPermanentAddress(candidateObj.getPermanentAddress());
		}
		if (candidateObj.getPurpose() != null) {
			newCandidate.setPurpose(candidateObj.getPurpose());
		}
		if (candidateObj.getRegistrationStatus() != null) {
			newCandidate.setRegistrationStatus(candidateObj.getRegistrationStatus());
		}
		if (candidateObj.getSkillSet() != null && !candidateObj.getSkillSet().isEmpty()) {
			newCandidate.setSkillSet(candidateObj.getSkillSet());
		}
		newCandidate.setUpdateAttributes(loggedInUser);
		Candidate persisted = candidateRepository.save(newCandidate);
		return persisted;
	}

	/**
	 * @param candidateObj
	 * @param newCandidate
	 * @return Candidate sets the base attributes
	 */
	private Candidate getUpdatedWithBaseAttributes(Candidate candidateObj, Candidate newCandidate) {
		newCandidate.setFirstName(candidateObj.getFirstName());
		newCandidate.setLastName(candidateObj.getLastName());
		newCandidate.setMiddleName(candidateObj.getMiddleName());
		newCandidate.setGender(candidateObj.getGender());
		newCandidate.setPersonalEmail(candidateObj.getPersonalEmail());
		newCandidate.setPersonalContactNo(candidateObj.getPersonalContactNo());
		newCandidate.setOfficialEmail(candidateObj.getOfficialEmail());
		newCandidate.setOfficialContactNo(candidateObj.getOfficialContactNo());
		newCandidate.setFamilyContact1(candidateObj.getFamilyContact1());
		newCandidate.setFamilyContact2(candidateObj.getFamilyContact2());
		newCandidate.setFamilyEmail(candidateObj.getFamilyEmail());
		newCandidate.setOnsiteContactNo(candidateObj.getOnsiteContactNo());
		newCandidate.setFathersName(candidateObj.getFathersName());
		newCandidate.setMothersName(candidateObj.getMothersName());
		newCandidate.setSpouseName(candidateObj.getSpouseName());
		newCandidate.setChildName(candidateObj.getChildName());
		newCandidate.setPriorExperienceMonth(candidateObj.getPriorExperienceMonth());
		newCandidate.setPriorExperienceYear(candidateObj.getPriorExperienceYear());
		newCandidate.setClient(candidateObj.getClient());
		newCandidate.setPartner(candidateObj.getPartner());
		newCandidate.setSourcedBy(candidateObj.getSourcedBy());
		newCandidate.setDob(candidateObj.getDob());
		newCandidate.setDoj(candidateObj.getDoj());
		return newCandidate;
	}

	/**
	 * @param bankAccount
	 * @param newCandidate
	 * @return Candidate sets the account details
	 */
	private Candidate setBankAccountInDetail(BankAccount bankAccount, Candidate newCandidate) {
		if (newCandidate.getBankAccount() == null) {
			newCandidate.setBankAccount(bankAccount);
			newCandidate.getBankAccount().setAccountHolderName(bankAccount.getAccountHolderName());
			newCandidate.getBankAccount().setAccountNo(bankAccount.getAccountNo());
			newCandidate.getBankAccount().setBankAddress(bankAccount.getBankAddress());
			newCandidate.getBankAccount().setBankName(bankAccount.getBankName());
			newCandidate.getBankAccount().setBranch(bankAccount.getBranch());
			newCandidate.getBankAccount().setIFSCCode(bankAccount.getIFSCCode());
		} else {
			newCandidate.setBankAccount(bankAccount);
		}
		return newCandidate;
	}

	/**
	 * @param loggedInUser
	 * @param candidate
	 * @return Candidate saves a new candidate to database
	 */
	private Candidate addCandidate(User loggedInUser, Candidate candidate) {
		candidate.setBaseAttributes(loggedInUser);
		Long increment = baseService.getSequenceIncrement(Candidate.class);
		String candidateId = SequenceGenerator.generateCandidateId(increment);
		candidate.setCandidateId(candidateId);
		Candidate persisted = candidateRepository.save(candidate);
		try{
			mailService.sendCandidateRegMail( persisted );
		}catch(MessagingException e){
			e.printStackTrace();
		}
		return persisted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.CandidateService#deleteCandidates(java.
	 * util.List)
	 */
	@Override
	public ResponseEntity<Void> deleteCandidates(List<Long> candidateIds) {
		User loggedInUser = SessionManager.getCurrentUserAsEntity();
		List<Candidate> candidates = candidateRepository.findByIdIn(candidateIds);
		for (Candidate candidate : candidates) {
			candidate.setDeleteAttributes(loggedInUser);
		}
		candidateRepository.save(candidates);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.belhopat.backoffice.service.CandidateService#deleteCandidate(java.
	 * lang.Long)
	 */
	@Override
	public ResponseEntity<ResponseObject> deleteCandidate(Long candidateId) {
		if (candidateId != null) {
			Candidate candidate = candidateRepository.findById(candidateId);
			User loggedInUser = SessionManager.getCurrentUserAsEntity();
			candidate.setUpdateAttributes(loggedInUser);
			candidate.setDeleteAttributes(loggedInUser);
			candidate = candidateRepository.save(candidate);
			if (candidate != null) {
				String candidateName = candidate.getFirstName() + " " + candidate.getLastName();
				return new ResponseEntity<ResponseObject>(
						new ResponseObject(true, candidateName + " successfully deleted"), HttpStatus.OK);
			}
		}
		return new ResponseEntity<ResponseObject>(new ResponseObject(true, "Oops..error while deleting!"),
				HttpStatus.OK);
	}

	@Override
	public List<SalaryGrade> getSalaryGrades() {
		List<SalaryGrade> grades = salaryGradeRepository.findAll();
		return grades;
	}

	@Override
	public DataTablesOutput<EmployeeSalary> getOfferLetters(DataTablesInput input) {
//		Specification<EmployeeSalary> specification = new Specification<EmployeeSalary>() {
//			@Override
//			public Predicate toPredicate(Root<EmployeeSalary> root, CriteriaQuery<?> criteriaQuery,
//					CriteriaBuilder criteriaBuilder) {
//				Predicate isNotDeleted = criteriaBuilder.equal(root.get("deleted"), false);
//				return criteriaBuilder.and(isNotDeleted);
//			}
//		};
		DataTablesOutput<EmployeeSalary> dataTablesOutput = employeeSalaryRepository
				.findAll(input/* , specification */);
		return dataTablesOutput;

	}

	@Override
	public DataTablesOutput<Candidate> getUnProcessedCandidates(DataTablesInput input, boolean employee) {
		Specification<Candidate> specification = new Specification<Candidate>() {
			@Override
			public Predicate toPredicate(Root<Candidate> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Predicate isNotDeleted = criteriaBuilder.equal(root.get("deleted"), false);
				Predicate employeeStatus = criteriaBuilder.equal(root.get("employee"), false);
				Predicate salaryNotProcessed = criteriaBuilder.equal(root.get("offerletterStatus"), false);
				return criteriaBuilder.and(isNotDeleted, employeeStatus, salaryNotProcessed);
			}
		};
		DataTablesOutput<Candidate> dataTablesOutput = candidateRepository.findAll(input, specification);
		return dataTablesOutput;
	}

	@Override
	public ResponseEntity<EmployeeSalary> requestForApproval(EmployeeSalary employeeSalary) {
		Task currentTask = baseService.createNewTaskList(TaskConstants.OFFER_LETTER_CREATION);
		employeeSalary.setCurrentTask(currentTask);
		EmployeeSalary empSal = employeeSalaryRepository.saveAndFlush(employeeSalary);
		return new ResponseEntity<EmployeeSalary>(empSal, HttpStatus.OK);
	}

}
