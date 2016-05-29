package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class OfficialCards {
	
	@Id
	@GeneratedValue
	private Long id;

	private String drivingLicenceNo;

	private String PANNo;

	private String ESINo;

	private String PFNo;

	private String forexCardNo;

	private String forexCardAgency;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "OfficialDetails [id=" + id + ", drivingLicenceNo=" + drivingLicenceNo + ", PANNo=" + PANNo + ", ESINo="
				+ ESINo + ", PFNo=" + PFNo + ", forexCardNo=" + forexCardNo + ", forexCardAgency=" + forexCardAgency
				+ "]";
	}

}
