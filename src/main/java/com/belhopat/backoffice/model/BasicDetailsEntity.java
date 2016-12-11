package com.belhopat.backoffice.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@MappedSuperclass
public class BasicDetailsEntity extends BaseEntity {

//	@Temporal(TemporalType.DATE)
	@Column(name = "DOB")
	private Date dob;

	// @Temporal(TemporalType.DATE)
	@Column(name = "DOJ")
	private Date doj;
	
	@ManyToOne
	@JoinColumn(name = "GNDR_ID", nullable = false)
	private LookupDetail gender;

	@Column(name = "PER_EMAIL", length = 50)
	private String personalEmail;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PER_CONT_NO_ID")
	private Phone personalContactNo;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Skill> skillSet;
	
	@Column(name = "PRIR_EXP_YR")
	private Integer priorExperienceYear;

	@Column(name = "PRIR_EXP_MNT")
	private Integer priorExperienceMonth;

	@ManyToOne
	@JoinColumn(name = "DSGNT_ID")
	private LookupDetail designation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PASSPORT_ID")
	private Passport passport;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FAM_CONT_NO1_ID")
	private Phone familyContact1;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FAM_CONT_NO2_ID")
	private Phone familyContact2;

	@Column(name = "FAM_EMAIL", length = 50)
	private String familyEmail;

	@Column(name = "SRCD_BY", length = 50)
	private String sourcedBy;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CURR_ADRS_ID")
	private Address currentAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PERM_ADRS_ID")
	private Address permanentAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BNK_ACC_ID")
	private BankAccount bankAccount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ONST_CONT_NO_ID")
	private Phone onsiteContactNo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ONST_ADRS_ID")
	private Address onsiteAddress;
	
	@ManyToOne
	@JoinColumn(name = "CNTY_OF_ORG_ID")
	private Country countryOfOrigin;
	
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

	public Set<Skill> getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(Set<Skill> skillSet) {
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

	public LookupDetail getDesignation() {
		return designation;
	}

	public void setDesignation(LookupDetail designation) {
		this.designation = designation;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
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

	public String getSourcedBy() {
		return sourcedBy;
	}

	public void setSourcedBy(String sourcedBy) {
		this.sourcedBy = sourcedBy;
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

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Phone getOnsiteContactNo() {
		return onsiteContactNo;
	}

	public void setOnsiteContactNo(Phone onsiteContactNo) {
		this.onsiteContactNo = onsiteContactNo;
	}

	public Address getOnsiteAddress() {
		return onsiteAddress;
	}

	public void setOnsiteAddress(Address onsiteAddress) {
		this.onsiteAddress = onsiteAddress;
	}

	public Country getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(Country countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	@Override
	public String toString() {
		return "BasicDetailsEntity [dob=" + dob + ", doj=" + doj + ", gender=" + gender + ", personalEmail="
				+ personalEmail + ", personalContactNo=" + personalContactNo + ", skillSet=" + skillSet
				+ ", priorExperienceYear=" + priorExperienceYear + ", priorExperienceMonth=" + priorExperienceMonth
				+ ", designation=" + designation + ", passport=" + passport + ", familyContact1=" + familyContact1
				+ ", familyContact2=" + familyContact2 + ", familyEmail=" + familyEmail + ", sourcedBy=" + sourcedBy
				+ ", currentAddress=" + currentAddress + ", permanentAddress=" + permanentAddress + ", bankAccount="
				+ bankAccount + ", onsiteContactNo=" + onsiteContactNo + ", onsiteAddress=" + onsiteAddress
				+ ", countryOfOrigin=" + countryOfOrigin + "]";
	}
	
}
