package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Country {

	@Id
	@GeneratedValue
	private Long id;
	
	private String code;
	
	private String description;
	
	@Column(name = "iso3")
	private String iso3Code;

	@Column(name = "phonecode")
	private Integer phoneCode;
	
	@Column(name = "numcode")
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
