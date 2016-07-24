package com.belhopat.backoffice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Candidate extends BaseEntity {

	private String candidateId;

	private String firstName;

	private String middleName;

	private String lastName;

	private Date dob;

	@ManyToOne
	private LookupDetail gender;

	@ManyToOne
	private LookupDetail bloodGroup;

	private String personalEmail;

	@OneToOne(cascade = CascadeType.ALL)
	private Phone personalContactNo;

	private String officialEmail;

	@OneToOne(cascade = CascadeType.ALL)
	private Phone officialContactNo;

	@OneToOne(cascade = CascadeType.ALL)
	private Phone familyContact1;

	@OneToOne(cascade = CascadeType.ALL)
	private Phone familyContact2;

	private String familyEmail;

	@OneToOne(cascade = CascadeType.ALL)
	private Phone onsiteContactNo;

	private String fathersName;

	private String mothersName;

	private String childName;

	private String spouseName;

	@OneToOne(fetch = FetchType.EAGER)
	private EmployeeSalary salary;

	@OneToOne(cascade = CascadeType.ALL)
	private Address currentAddress;

	@OneToOne(cascade = CascadeType.ALL)
	private Address permanentAddress;

	@OneToOne(cascade = CascadeType.ALL)
	private Address onsiteAddress;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Skill> skillSet;

	private Integer priorExperienceYear;

	private Integer priorExperienceMonth;

	@ManyToOne
	private Country countryOfOrigin;

	@ManyToOne
	private Country countryToVisit;

	@ManyToOne
	private Client client;

	@ManyToOne
	private Client sourcedBy;

	private String partner;

	@OneToOne(cascade = CascadeType.ALL)
	private BankAccount bankAccount;

	@OneToOne(cascade = CascadeType.ALL)
	private Passport passport;

	@OneToOne(cascade = CascadeType.ALL)
	private OfficialCards officialDetails;

	private Date doj;

	@ManyToOne
	private LookupDetail division;

	@ManyToOne
	private LookupDetail designation;

	@ManyToOne
	private LookupDetail purpose;

	@ManyToOne
	private LookupDetail employmentStatus;

	@ManyToOne
	private LookupDetail registrationStatus;

	private boolean employee;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public LookupDetail getGender() {
		return gender;
	}

	public void setGender(LookupDetail gender) {
		this.gender = gender;
	}

	public LookupDetail getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(LookupDetail bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public Phone getPersonalContactNo() {
		return personalContactNo;
	}

	public void setPersonalContactNo(Phone personalContactNo) {
		this.personalContactNo = personalContactNo;
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

	public Phone getFamilyContact1() {
		return familyContact1;
	}

	public void setFamilyContact1(Phone familyContact1) {
		this.familyContact1 = familyContact1;
	}

	public Phone getFamilyContact2() {
		return familyContact2;
	}

	public void setFamilyContact2(Phone familyContact2) {
		this.familyContact2 = familyContact2;
	}

	public String getFamilyEmail() {
		return familyEmail;
	}

	public void setFamilyEmail(String familyEmail) {
		this.familyEmail = familyEmail;
	}

	public Phone getOnsiteContactNo() {
		return onsiteContactNo;
	}

	public void setOnsiteContactNo(Phone onsiteContactNo) {
		this.onsiteContactNo = onsiteContactNo;
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

	public Address getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Address getOnsiteAddress() {
		return onsiteAddress;
	}

	public void setOnsiteAddress(Address onsiteAddress) {
		this.onsiteAddress = onsiteAddress;
	}

	public List<Skill> getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(List<Skill> skillSet) {
		this.skillSet = skillSet;
	}

	public Integer getPriorExperienceYear() {
		return priorExperienceYear;
	}

	public void setPriorExperienceYear(Integer priorExperienceYear) {
		this.priorExperienceYear = priorExperienceYear;
	}

	public Integer getPriorExperienceMonth() {
		return priorExperienceMonth;
	}

	public void setPriorExperienceMonth(Integer priorExperienceMonth) {
		this.priorExperienceMonth = priorExperienceMonth;
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

	public Client getSourcedBy() {
		return sourcedBy;
	}

	public void setSourcedBy(Client sourcedBy) {
		this.sourcedBy = sourcedBy;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public OfficialCards getOfficialDetails() {
		return officialDetails;
	}

	public void setOfficialDetails(OfficialCards officialDetails) {
		this.officialDetails = officialDetails;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public LookupDetail getDivision() {
		return division;
	}

	public void setDivision(LookupDetail division) {
		this.division = division;
	}

	public LookupDetail getDesignation() {
		return designation;
	}

	public void setDesignation(LookupDetail designation) {
		this.designation = designation;
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

	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender + ", bloodGroup=" + bloodGroup
				+ ", personalEmail=" + personalEmail + ", personalContactNo=" + personalContactNo + ", officialEmail="
				+ officialEmail + ", officialContactNo=" + officialContactNo + ", familyContact1=" + familyContact1
				+ ", familyContact2=" + familyContact2 + ", familyEmail=" + familyEmail + ", onsiteContactNo="
				+ onsiteContactNo + ", fathersName=" + fathersName + ", mothersName=" + mothersName + ", childName="
				+ childName + ", spouseName=" + spouseName + ", salary=" + salary + ", currentAddress=" + currentAddress
				+ ", permanentAddress=" + permanentAddress + ", onsiteAddress=" + onsiteAddress + ", skillSet="
				+ skillSet + ", priorExperienceYear=" + priorExperienceYear + ", priorExperienceMonth="
				+ priorExperienceMonth + ", countryOfOrigin=" + countryOfOrigin + ", countryToVisit=" + countryToVisit
				+ ", client=" + client + ", sourcedBy=" + sourcedBy + ", partner=" + partner + ", bankAccount="
				+ bankAccount + ", passport=" + passport + ", officialDetails=" + officialDetails + ", doj=" + doj
				+ ", division=" + division + ", designation=" + designation + ", purpose=" + purpose
				+ ", employmentStatus=" + employmentStatus + ", registrationStatus=" + registrationStatus
				+ ", employee=" + employee + "]";
	}

}
