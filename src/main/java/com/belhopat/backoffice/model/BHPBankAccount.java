package com.belhopat.backoffice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BHP_BANK_ACCOUNT")
public class BHPBankAccount {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "ACC_NO", length = 25)
	private String accountNo;

	@ManyToOne
	@JoinColumn(name = "ACC_TYPE_ID")
	private LookupDetail accountType;

	@ManyToOne
	@JoinColumn(name = "CURR_ID")
	private Currency currency;

	@ManyToOne
	@JoinColumn(name = "CNTRY_ID")
	private Country country;

	@Column(name = "BAL_AMT", scale = 2)
	private Double balanceAmount;

	@Column(name = "IFSC_CODE", length = 25)
	private String IFSCCode;

	@Column(name = "MICR_CODE", length = 25)
	private String MICRCode;

	@Column(name = "SWIFT_CODE", length = 25)
	private String swiftCode;

	@Column(name = "IBAN", length = 25)
	private String iban;

	@Column(name = "ACC_HOLD_NAME", length = 50)
	private String accountHolderName;

	@Column(name = "BNK_NAME", length = 50)
	private String bankName;

	@Column(name = "BRANCH", length = 50)
	private String branch;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BNK_ADRS_ID")
	private Address bankAddress;

	@Column(name = "CON_DTLS", length = 100)
	private String contactDetails;

	@Column(name = "PURPOSE", length = 100)
	private String purpose;

	@Column(name = "ADTNL_FLD_1", length = 100)
	private String additionalField1;

	@Column(name = "ADTNL_FLD_2", length = 100)
	private String additionalField2;

	@Column(name = "ADTNL_FLD_3", length = 100)
	private String additionalField3;

	@Column(name = "ADTNL_FLD_4", length = 100)
	private String additionalField4;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public LookupDetail getAccountType() {
		return accountType;
	}

	public void setAccountType(LookupDetail accountType) {
		this.accountType = accountType;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getIFSCCode() {
		return IFSCCode;
	}

	public void setIFSCCode(String iFSCCode) {
		IFSCCode = iFSCCode;
	}

	public String getMICRCode() {
		return MICRCode;
	}

	public void setMICRCode(String mICRCode) {
		MICRCode = mICRCode;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Address getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(Address bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getAdditionalField1() {
		return additionalField1;
	}

	public void setAdditionalField1(String additionalField1) {
		this.additionalField1 = additionalField1;
	}

	public String getAdditionalField2() {
		return additionalField2;
	}

	public void setAdditionalField2(String additionalField2) {
		this.additionalField2 = additionalField2;
	}

	public String getAdditionalField3() {
		return additionalField3;
	}

	public void setAdditionalField3(String additionalField3) {
		this.additionalField3 = additionalField3;
	}

	public String getAdditionalField4() {
		return additionalField4;
	}

	public void setAdditionalField4(String additionalField4) {
		this.additionalField4 = additionalField4;
	}

	@Override
	public String toString() {
		return "BHPBankAccount [id=" + id + ", accountNo=" + accountNo + ", accountType=" + accountType + ", currency="
				+ currency + ", country=" + country + ", balanceAmount=" + balanceAmount + ", IFSCCode=" + IFSCCode
				+ ", MICRCode=" + MICRCode + ", swiftCode=" + swiftCode + ", iban=" + iban + ", accountHolderName="
				+ accountHolderName + ", bankName=" + bankName + ", branch=" + branch + ", bankAddress=" + bankAddress
				+ ", contactDetails=" + contactDetails + ", purpose=" + purpose + ", additionalField1="
				+ additionalField1 + ", additionalField2=" + additionalField2 + ", additionalField3=" + additionalField3
				+ ", additionalField4=" + additionalField4 + "]";
	}
}
