package com.belhopat.backoffice.dto;

import java.util.Date;

/**
 * @author BHP_DEV request POJO data transfer object
 *
 */
public class EmployeeViewDTO {

	private String employeeId;

	private Date joiningDate;

	private String officialEmail;

	private String businessUnit;

	private String accountManager;

	private String hrRecruiter;

	private String hrManager;

	private String reportingManager;

	private String workLocation;

	private String baseLocation;

	private String timeZone;

	private CandidateViewDTO candidate;

	private EmploymentInfoDTO employment;

	private OfficialInfoDTO official;

	private FamilyInfoDTO family;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getOfficialEmail() {
		return officialEmail;
	}

	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	public String getHrRecruiter() {
		return hrRecruiter;
	}

	public void setHrRecruiter(String hrRecruiter) {
		this.hrRecruiter = hrRecruiter;
	}

	public String getHrManager() {
		return hrManager;
	}

	public void setHrManager(String hrManager) {
		this.hrManager = hrManager;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public CandidateViewDTO getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateViewDTO candidate) {
		this.candidate = candidate;
	}

	public EmploymentInfoDTO getEmployment() {
		return employment;
	}

	public void setEmployment(EmploymentInfoDTO employment) {
		this.employment = employment;
	}

	public OfficialInfoDTO getOfficial() {
		return official;
	}

	public void setOfficial(OfficialInfoDTO official) {
		this.official = official;
	}

	public FamilyInfoDTO getFamily() {
		return family;
	}

	public void setFamily(FamilyInfoDTO family) {
		this.family = family;
	}

}