package com.belhopat.backoffice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

	@Column(name = "EMP_ID", unique = true, nullable = false, length = 15)
	private String employeeId;

	@OneToOne
	@JoinColumn(name = "EMP_USR_ID")
	private User employeeUser;

	@Temporal(TemporalType.DATE)
	@Column(name = "JOIN_DATE")
	private Date joiningDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CNDT_ID")
	private Candidate employeeMaster;

	@Column(name = "OFC_EMAIL", length = 50)
	private String officialEmail;

	@ManyToOne
	@JoinColumn(name = "BUS_UNT_ID")
	private LookupDetail businessUnit;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACC_MNGR_ID")
	private Employee accountManager;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "HR_RCTR_ID")
	private Employee hrRecruiter;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "HR_MNGR_ID")
	private Employee hrManager;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RPTNG_MNGR_ID")
	private Employee reportingManager;

	@ManyToOne
	@JoinColumn(name = "WRK_LCTN_ID")
	private Country workLocation;

	@ManyToOne
	@JoinColumn(name = "BASE_LOC_ID")
	private Country baseLocation;

	@ManyToOne
	@JoinColumn(name = "TIME_ZONE_ID")
	private TimeZone timeZone;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

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

	public Country getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(Country workLocation) {
		this.workLocation = workLocation;
	}

	public Country getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(Country baseLocation) {
		this.baseLocation = baseLocation;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeUser=" + employeeUser + ", joiningDate=" + joiningDate
				+ ", employeeMaster=" + employeeMaster + ", officialEmail=" + officialEmail + ", businessUnit="
				+ businessUnit + ", accountManager=" + accountManager + ", hrRecruiter=" + hrRecruiter + ", hrManager="
				+ hrManager + ", reportingManager=" + reportingManager + ", workLocation=" + workLocation
				+ ", baseLocation=" + baseLocation + ", timeZone=" + timeZone + "]";
	}

}
