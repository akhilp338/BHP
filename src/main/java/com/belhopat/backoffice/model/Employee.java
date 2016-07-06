package com.belhopat.backoffice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Employee")
public class Employee extends BaseEntity{

	
	@OneToOne(cascade=CascadeType.ALL)
	private User employeeUser;
	
	private Date joiningDate;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Candidate employeeMaster;
	
	private String officialEmail;
	
	private String employeeId;

	@ManyToOne
	private LookupDetail businessUnit;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private Employee accountManager;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private Employee hrRecruiter;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private Employee hrManager;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private Employee reportingManager;
	
	@ManyToOne
	private City workLocation;
	
	@ManyToOne
	private TimeZone timeZone;

	public User getEmployeeUser() {
		return employeeUser;
	}

	public void setEmployeeUser(User employeeUser) {
		this.employeeUser = employeeUser;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Candidate getEmployeeMaster() {
		return employeeMaster;
	}

	public void setEmployeeMaster(Candidate employeeMaster) {
		this.employeeMaster = employeeMaster;
	}

	public String getOfficialEmail() {
		return officialEmail;
	}

	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LookupDetail getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(LookupDetail businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Employee getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(Employee accountManager) {
		this.accountManager = accountManager;
	}

	public Employee getHrRecruiter() {
		return hrRecruiter;
	}

	public void setHrRecruiter(Employee hrRecruiter) {
		this.hrRecruiter = hrRecruiter;
	}

	public Employee getHrManager() {
		return hrManager;
	}

	public void setHrManager(Employee hrManager) {
		this.hrManager = hrManager;
	}

	public Employee getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(Employee reportingManager) {
		this.reportingManager = reportingManager;
	}

	public City getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(City workLocation) {
		this.workLocation = workLocation;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
}
