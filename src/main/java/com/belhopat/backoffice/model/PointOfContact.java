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
@Table(name = "POINT_OF_CONTACT")
public class PointOfContact {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "POC_NM", nullable = false)
	private String pocName;

	@ManyToOne
	@JoinColumn(name = "POC_DSGTN_ID")
	private LookupDetail pocDesignation;

	@ManyToOne
	@JoinColumn(name = "POC_CNTRY_ID")
	private Country pocCountry;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CONT_NO_ID")
	private Phone contactNo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "MOB_NO_ID")
	private Phone mobNo;

	@Column(name = "AREA_OF_WRK", length = 25)
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

	public Phone getContactNo() {
		return contactNo;
	}

	public void setContactNo(Phone contactNo) {
		this.contactNo = contactNo;
	}

	public Phone getMobNo() {
		return mobNo;
	}

	public void setMobNo(Phone mobNo) {
		this.mobNo = mobNo;
	}

	public String getAreaOfWork() {
		return areaOfWork;
	}

	public void setAreaOfWork(String areaOfWork) {
		this.areaOfWork = areaOfWork;
	}

	@Override
	public String toString() {
		return "PointOfContact [id=" + id + ", pocName=" + pocName + ", pocDesignation=" + pocDesignation
				+ ", pocCountry=" + pocCountry + ", contactNo=" + contactNo + ", mobNo=" + mobNo + ", areaOfWork="
				+ areaOfWork + "]";
	}

}
