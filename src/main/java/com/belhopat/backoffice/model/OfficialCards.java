package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OFFICIAL_CARDS")
public class OfficialCards {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "DRV_LIC_NO", length = 25)
	private String drivingLicenceNo;

	@Column(name = "PAN_NO", length = 25)
	private String PANNo;

	@Column(name = "ESI_NO", length = 25)
	private String ESINo;

	@Column(name = "PF_NO", length = 25)
	private String PFNo;

	@Column(name = "FORX_CDR_NO", length = 25)
	private String forexCardNo;

	@Column(name = "FORX_CRD_AGNCY", length = 25)
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
