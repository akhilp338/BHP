package com.belhopat.backoffice.dto;

/**
 * @author BHP_DEV request POJO data transfer object
 *
 */
public class EmploymentInfoDTO {

	private String experience;

	private String doj;

	private String division;

	private String designation;

	private String employmentStatus;

	private String purpose;

	private String client;

	private String partner;

	private String sourcedBy;

	private String skillSet;

	private AddressDTO onsiteAddress;

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSourcedBy() {
		return sourcedBy;
	}

	public void setSourcedBy(String sourcedBy) {
		this.sourcedBy = sourcedBy;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public AddressDTO getOnsiteAddress() {
		return onsiteAddress;
	}

	public void setOnsiteAddress(AddressDTO onsiteAddress) {
		this.onsiteAddress = onsiteAddress;
	}

}