package com.belhopat.backoffice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table ( name = "CLIENT_DTLS" )
public class Client extends BaseEntity {
	
//	@NotNull
	@Column( name = "CLNT_CID", unique = true )
	private String clientId;
	
//	@NotNull
	@Column( name = "CLNT_NM", unique = true )
	private String clientName;
	
//	@NotNull
	@Column( name = "RVNUE_PC" )
	private Float revenue;
	
//	@NotNull
	@ManyToOne
	@JoinColumn( name = "BHP_AM" )
	private Employee accountManager;
	
//	@NotNull
	@ManyToOne
	@JoinColumn( name = "BHP_BDM" )
	private Employee bussDManager;

//	@NotNull
	@ManyToOne
	@JoinColumn( name = "BU_HD" )
	private Employee bussUnitHead;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "CLNT_ADDR" )
	private Address clientAddress;
	
//	@NotNull
	@Column( name = "PH_NO" )
	private String contactNo;

//	@NotNull
	@Column( name = "EML_ID" )
	private String emailId;

//	@NotNull
	@Column( name = "WEB_URL" )
	private String webUrl;
	
	@ManyToOne
	@JoinColumn( name = "CLNT_ST" )
	private LookupDetail clientStatus;
	
	@ManyToOne
	@JoinColumn( name = "BU" )
	private LookupDetail businessUnit;

//	@JsonIgnore
//    @NotNull
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable( name="CLNT_POC", 
//    joinColumns=@JoinColumn ( name = "CLNT_ID" ), 
//    inverseJoinColumns=@JoinColumn( name = "POC_ID" ) ) 
	@JoinColumn( name = "POC_ID" )
	private List < PointOfContact > pointOfContactList;

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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public List<PointOfContact> getPointOfContactList() {
		return pointOfContactList;
	}

	public void setPointOfContactList(List<PointOfContact> pointOfContactList) {
		this.pointOfContactList = pointOfContactList;
	}
	
	public LookupDetail getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(LookupDetail businessUnit) {
		this.businessUnit = businessUnit;
	}
	
}
