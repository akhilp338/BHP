package com.belhopat.backoffice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CANDIDATE")
public class Candidate extends BasicDetailsEntity {

	@Column(name = "CAND_ID", unique = true, nullable = false, length = 15)
	private String candidateId;

	@Column(name = "FRST_NAME", length = 50)
	private String firstName;

	@Column(name = "MDLE_NAME", length = 50)
	private String middleName;

	@Column(name = "LST_NAME", length = 50)
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "BLD_GRP_ID")
	private LookupDetail bloodGroup;

	@Column(name = "OFC_EMAIL", length = 50)
	private String officialEmail;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "OFC_CONT_NO_ID")
	private Phone officialContactNo;

	@Column(name = "FATR_NAME", length = 50)
	private String fathersName;

	@Column(name = "MOTR_NAME", length = 50)
	private String mothersName;

	@Column(name = "CHLD_NAME", length = 50)
	private String childName;

	@Column(name = "SPOS_NAME", length = 50)
	private String spouseName;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMP_SALARY_ID")
	private EmployeeSalary salary;

	@ManyToOne
	@JoinColumn(name = "CNTY_OF_ORG_ID")
	private Country countryOfOrigin;

	@ManyToOne
	@JoinColumn(name = "CNTY_TO_VST_ID")
	private Country countryToVisit;

	@ManyToOne
	@JoinColumn(name = "CLNT_ID")
	private Client client;

	@Column(name = "PARTNER", length = 50)
	private String partner;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "OFC_DTLS_ID")
	private OfficialCards officialDetails;

	@ManyToOne
	@JoinColumn(name = "DVSN_ID")
	private LookupDetail division;

	@ManyToOne
	@JoinColumn(name = "PRPUS_ID")
	private LookupDetail purpose;

	@ManyToOne
	@JoinColumn(name = "EMP_STS_ID")
	private LookupDetail employmentStatus;

	@ManyToOne
	@JoinColumn(name = "REG_STS_ID")
	private LookupDetail registrationStatus;

	@Column(name = "IS_EMP", columnDefinition = "boolean default false", nullable = false)
	private boolean employee;

	@Column(name = "IS_OFR_LTR_GNRTD", columnDefinition = "boolean default false", nullable = false)
	private boolean offerletterStatus;

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LookupDetail getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(LookupDetail bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getOfficialEmail() {
		return officialEmail;
	}

	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}

	public Phone getOfficialContactNo() {
		return officialContactNo;
	}

	public void setOfficialContactNo(Phone officialContactNo) {
		this.officialContactNo = officialContactNo;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public EmployeeSalary getSalary() {
		return salary;
	}

	public void setSalary(EmployeeSalary salary) {
		this.salary = salary;
	}

	public Country getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(Country countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public Country getCountryToVisit() {
		return countryToVisit;
	}

	public void setCountryToVisit(Country countryToVisit) {
		this.countryToVisit = countryToVisit;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public OfficialCards getOfficialDetails() {
		return officialDetails;
	}

	public void setOfficialDetails(OfficialCards officialDetails) {
		this.officialDetails = officialDetails;
	}

	public LookupDetail getDivision() {
		return division;
	}

	public void setDivision(LookupDetail division) {
		this.division = division;
	}

	public LookupDetail getPurpose() {
		return purpose;
	}

	public void setPurpose(LookupDetail purpose) {
		this.purpose = purpose;
	}

	public LookupDetail getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(LookupDetail employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public LookupDetail getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(LookupDetail registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public boolean isEmployee() {
		return employee;
	}

	public void setEmployee(boolean employee) {
		this.employee = employee;
	}

	public boolean isOfferletterStatus() {
		return offerletterStatus;
	}

	public void setOfferletterStatus(boolean offerletterStatus) {
		this.offerletterStatus = offerletterStatus;
	}

	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", bloodGroup=" + bloodGroup + ", officialEmail=" + officialEmail
				+ ", officialContactNo=" + officialContactNo + ", fathersName=" + fathersName + ", mothersName="
				+ mothersName + ", childName=" + childName + ", spouseName=" + spouseName + ", salary=" + salary
				+ ", countryOfOrigin=" + countryOfOrigin + ", countryToVisit=" + countryToVisit + ", client=" + client
				+ ", partner=" + partner + ", officialDetails=" + officialDetails + ", division=" + division
				+ ", purpose=" + purpose + ", employmentStatus=" + employmentStatus + ", registrationStatus="
				+ registrationStatus + ", employee=" + employee + ", offerletterStatus=" + offerletterStatus + "]";
	}

	
}
