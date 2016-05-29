package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table ( name = "POINT_OF_CONTACT" )
public class PointOfContact {
	
	@Id
	@GeneratedValue
	@Column( name = "ID" )
	private Long id;

	@NotNull
	@Column( name = "POC_NM", unique = true )
	private String pocName;
	
	@ManyToOne
	@NotNull
	@JoinColumn( name = "POC_DSGNTN" )
	private LookupDetail pocDesignation;
	
	@ManyToOne
	@NotNull
	@JoinColumn( name = "POC_CNTRY" )
	private Country pocCountry;
	
	@NotNull
	@Column( name = "PH_NO" )
	private String contactNo;
	
	@Column( name = "MOB_NO" )
	private String mobNo;

	@NotNull
	@Column( name = "WORK_AR" )
	private String areaOfWork;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPocName() {
		return pocName;
	}

	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	public LookupDetail getPocDesignation() {
		return pocDesignation;
	}

	public void setPocDesignation(LookupDetail pocDesignation) {
		this.pocDesignation = pocDesignation;
	}

	public Country getPocCountry() {
		return pocCountry;
	}

	public void setPocCountry(Country pocCountry) {
		this.pocCountry = pocCountry;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getMobnO() {
		return mobNo;
	}

	public void setMobnO(String mobnO) {
		this.mobNo = mobnO;
	}

	public String getAreaOfWork() {
		return areaOfWork;
	}

	public void setAreaOfWork(String areaOfWork) {
		this.areaOfWork = areaOfWork;
	}
	
}
