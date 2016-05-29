package com.belhopat.backoffice.dto;

import java.util.Date;

public class EmployeeDto {

	private Long id;
	
	private Long employeeMasterId;
	
	private Long hrManager;
	
	private Long accountManager;
	
	private Long businessUnit;
	
	private Date joiningDate;
	
	private Long workLocation;
	
	private Long timeZone;

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

	public Long getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Long timeZone) {
		this.timeZone = timeZone;
	}
	
	
	
}
