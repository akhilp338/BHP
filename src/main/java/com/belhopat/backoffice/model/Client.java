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
@Table(name = "CLIENT")
public class Client extends BaseEntity {

	@Column(name = "CLNT_ID", unique = true, nullable = false, length = 15)
	private String clientId;

	@Column(name = "CLNT_NAME", length = 50)
	private String clientName;

	@Column(name = "REVENUE", scale = 2)
	private Float revenue;

	@ManyToOne
	@JoinColumn(name = "ACC_MNGR_ID")
	private Employee accountManager;

	@ManyToOne
	@JoinColumn(name = "BUS_DEV_MNGR_ID")
	private Employee bussDManager;

	@ManyToOne
	@JoinColumn(name = "BUS_UNT_HED_ID")
	private Employee bussUnitHead;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CLNT_ADRS_ID")
	private Address clientAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CONT_NO_ID")
	private Phone contactNo;

	@Column(name = "EMAIL", length = 50)
	private String email;

	@Column(name = "WEB_URL")
	private String webUrl;

	@ManyToOne
	@JoinColumn(name = "CLNT_STS_ID")
	private LookupDetail clientStatus;

	@ManyToOne
	@JoinColumn(name = "BUS_UNT_ID")
	private LookupDetail businessUnit;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PONT_OF_CONT_ID")
	private PointOfContact poc;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Float getRevenue() {
		return revenue;
	}

	public void setRevenue(Float revenue) {
		this.revenue = revenue;
	}

	public Employee getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(Employee accountManager) {
		this.accountManager = accountManager;
	}

	public Employee getBussDManager() {
		return bussDManager;
	}

	public void setBussDManager(Employee bussDManager) {
		this.bussDManager = bussDManager;
	}

	public Employee getBussUnitHead() {
		return bussUnitHead;
	}

	public void setBussUnitHead(Employee bussUnitHead) {
		this.bussUnitHead = bussUnitHead;
	}

	public Address getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(Address clientAddress) {
		this.clientAddress = clientAddress;
	}

	public Phone getContactNo() {
		return contactNo;
	}

	public void setContactNo(Phone contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public LookupDetail getClientStatus() {
		return clientStatus;
	}

	public void setClientStatus(LookupDetail clientStatus) {
		this.clientStatus = clientStatus;
	}

	public LookupDetail getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(LookupDetail businessUnit) {
		this.businessUnit = businessUnit;
	}

	public PointOfContact getPoc() {
		return poc;
	}

	public void setPoc(PointOfContact poc) {
		this.poc = poc;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", clientName=" + clientName + ", revenue=" + revenue
				+ ", accountManager=" + accountManager + ", bussDManager=" + bussDManager + ", bussUnitHead="
				+ bussUnitHead + ", clientAddress=" + clientAddress + ", contactNo=" + contactNo + ", email=" + email
				+ ", webUrl=" + webUrl + ", clientStatus=" + clientStatus + ", businessUnit=" + businessUnit + ", poc="
				+ poc + "]";
	}

}
