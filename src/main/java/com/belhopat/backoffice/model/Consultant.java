package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( name = "CONSULTANT" )
public class Consultant extends BasicDetailsEntity {

	@Column(name = "CONS_ID", unique = true, nullable = false, length = 15)
	private String consultantId;

	@Column(name = "FULL_NAME", length = 100)
	private String fullName;

    @ManyToOne
    @JoinColumn( name = "WRK_LCTN_ID" )
    private Country workLocation;
    
    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ACC_MNGR_ID" )
    private Employee accountManager;
    
    @ManyToOne
    @JoinColumn( name = "BUS_UNT_ID" )
    private LookupDetail businessUnit;
    
    @ManyToOne
    @JoinColumn( name = "STATUS_ID" )
    private LookupDetail status;

	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Country getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(Country workLocation) {
		this.workLocation = workLocation;
	}

	public Employee getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(Employee accountManager) {
		this.accountManager = accountManager;
	}

	public LookupDetail getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(LookupDetail businessUnit) {
		this.businessUnit = businessUnit;
	}

	public LookupDetail getStatus() {
		return status;
	}

	public void setStatus(LookupDetail status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Consultant [consultantId=" + consultantId + ", fullName=" + fullName + ", workLocation=" + workLocation
				+ ", accountManager=" + accountManager + ", businessUnit=" + businessUnit + ", status=" + status + "]";
	}
    
}