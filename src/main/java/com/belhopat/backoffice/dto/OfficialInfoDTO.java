package com.belhopat.backoffice.dto;

/**
 * @author BHP_DEV request POJO data transfer object
 *
 */
public class OfficialInfoDTO {

	private String bankAccountNo;

	private String bankIFSCCode;

	private String bankAccountHolderName;

	private String bankName;

	private String bankBranch;

	private AddressDTO bankAddress;

	private String passportNo;

	private String passportIssueDate;

	private String passportExpiryDate;

	private String passportCountry;

	private String drivingLicenceNo;

	private String PANNo;

	private String ESINo;

	private String PFNo;

	private String forexCardNo;

	private String forexCardAgency;

	public String getBankAccountNo() {
		return bankAccountNo;
	}

	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankIFSCCode() {
		return bankIFSCCode;
	}

	public void setBankIFSCCode(String bankIFSCCode) {
		this.bankIFSCCode = bankIFSCCode;
	}

	public String getBankAccountHolderName() {
		return bankAccountHolderName;
	}

	public void setBankAccountHolderName(String bankAccountHolderName) {
		this.bankAccountHolderName = bankAccountHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public AddressDTO getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(AddressDTO bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getPassportIssueDate() {
		return passportIssueDate;
	}

	public void setPassportIssueDate(String passportIssueDate) {
		this.passportIssueDate = passportIssueDate;
	}

	public String getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(String passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public String getPassportCountry() {
		return passportCountry;
	}

	public void setPassportCountry(String passportCountry) {
		this.passportCountry = passportCountry;
	}

	public String getDrivingLicenceNo() {
		return drivingLicenceNo;
	}

	public void setDrivingLicenceNo(String drivingLicenceNo) {
		this.drivingLicenceNo = drivingLicenceNo;
	}

	public String getPANNo() {
		return PANNo;
	}

	public void setPANNo(String pANNo) {
		PANNo = pANNo;
	}

	public String getESINo() {
		return ESINo;
	}

	public void setESINo(String eSINo) {
		ESINo = eSINo;
	}

	public String getPFNo() {
		return PFNo;
	}

	public void setPFNo(String pFNo) {
		PFNo = pFNo;
	}

	public String getForexCardNo() {
		return forexCardNo;
	}

	public void setForexCardNo(String forexCardNo) {
		this.forexCardNo = forexCardNo;
	}

	public String getForexCardAgency() {
		return forexCardAgency;
	}

	public void setForexCardAgency(String forexCardAgency) {
		this.forexCardAgency = forexCardAgency;
	}

}