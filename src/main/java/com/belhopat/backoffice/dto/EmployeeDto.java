package com.belhopat.backoffice.dto;

import java.util.Date;

public class EmployeeDto {

	private Long id;
	
	private Long employeeMasterId;
	
	private Long hrManager;
	
	private Long hrRecruiter;
	
	private Long workLocation;
	
	private Long reportingManager;
	
	private Long accountManager;
	
	private Long businessUnit;
	
	private Date joiningDate;
	
	private String employeeId;
	
	private String mail;
	
	private Long baseLocation;
	
	private String officialEmailId;
	
	
	public String getOfficialEmailId() {
		return officialEmailId;
	}

	public void setOfficialEmailId(String officialEmailId) {
		this.officialEmailId = officialEmailId;
	}

	public Long getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(Long baseLocation) {
		this.baseLocation = baseLocation;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Long getHrRecruiter() {
		return hrRecruiter;
	}

	public void setHrRecruiter(Long hrRecruiter) {
		this.hrRecruiter = hrRecruiter;
	}

	public Long getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(Long reportingManager) {
		this.reportingManager = reportingManager;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployeeMasterId() {
		return employeeMasterId;
	}

	public void setEmployeeMasterId(Long employeeMasterId) {
		this.employeeMasterId = employeeMasterId;
	}

	public Long getHrManager() {
		return hrManager;
	}

	public void setHrManager(Long hrManager) {
		this.hrManager = hrManager;
	}

	public Long getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(Long accountManager) {
		this.accountManager = accountManager;
	}

	public Long getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(Long businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Long getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(Long workLocation) {
		this.workLocation = workLocation;
	}

	
	
	
}
