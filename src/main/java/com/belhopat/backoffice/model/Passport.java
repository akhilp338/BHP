package com.belhopat.backoffice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PASSPORT")
public class Passport {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "PSPRT_NO", length = 25)
	private String passportNo;

	// @Temporal(TemporalType.DATE)
	@Column(name = "ISS_DATE")
	private Date issueDate;

	// @Temporal(TemporalType.DATE)
	@Column(name = "EXP_DATE")
	private Date expiryDate;

	@ManyToOne
	@JoinColumn(name = "CNTRY_ID")
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", passportNo=" + passportNo + ", issueDate=" + issueDate + ", expiryDate="
				+ expiryDate + ", country=" + country + "]";
	}

}
