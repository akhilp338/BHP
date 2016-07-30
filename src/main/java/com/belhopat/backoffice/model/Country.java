package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class Country {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "CODE", nullable = false, length = 10)
	private String code;

	@Column(name = "DESC", length = 50)
	private String description;

	@Column(name = "ISO_3_CODE", length = 10)
	private String iso3Code;

	@Column(name = "PH_CODE")
	private Integer phoneCode;

	@Column(name = "NUM_CODE")
	private Integer numberCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIso3Code() {
		return iso3Code;
	}

	public void setIso3Code(String iso3Code) {
		this.iso3Code = iso3Code;
	}

	public Integer getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(Integer phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Integer getNumberCode() {
		return numberCode;
	}

	public void setNumberCode(Integer numberCode) {
		this.numberCode = numberCode;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", code=" + code + ", description=" + description + ", iso3Code=" + iso3Code
				+ ", phoneCode=" + phoneCode + ", numberCode=" + numberCode + "]";
	}

}
