package com.belhopat.backoffice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BusinessUnit {

	@GeneratedValue
	@Id
	private Long Id;
	
	private String businessUnitName;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}
	
	
}
