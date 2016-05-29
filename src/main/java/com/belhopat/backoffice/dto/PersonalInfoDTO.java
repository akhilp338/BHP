package com.belhopat.backoffice.dto;

/**
 * @author BHP_DEV
 * request POJO data transfer object
 *
 */
public class PersonalInfoDTO {
	
	private String firstName;

	private String middleName;

	private String lastName;
	
	private String dob;

	private String gender;
	
	private String bloodGroup;
	
	private String countryOfOrigin;
	
	private String personalContactNo;
	
	private String personalEmailId;
	
	private String officialContactNo;
	
	private String officialEmailId;
	
	private AddressDTO permenantAddress;

	private AddressDTO currentAddress;

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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getPersonalContactNo() {
		return personalContactNo;
	}

	public void setPersonalContactNo(String personalContactNo) {
		this.personalContactNo = personalContactNo;
	}

	public String getPersonalEmailId() {
		return personalEmailId;
	}

	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}

	public String getOfficialContactNo() {
		return officialContactNo;
	}

	public void setOfficialContactNo(String officialContactNo) {
		this.officialContactNo = officialContactNo;
	}

	public String getOfficialEmailId() {
		return officialEmailId;
	}

	public void setOfficialEmailId(String officialEmailId) {
		this.officialEmailId = officialEmailId;
	}

	public AddressDTO getPermenantAddress() {
		return permenantAddress;
	}

	public void setPermenantAddress(AddressDTO permenantAddress) {
		this.permenantAddress = permenantAddress;
	}

	public AddressDTO getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(AddressDTO currentAddress) {
		this.currentAddress = currentAddress;
	}
	
}