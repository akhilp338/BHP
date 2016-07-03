package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TimeZone {
	@GeneratedValue
	@Id
	private Long id;
	
	@Column( name = "UTC_OFFSET")
	private String offsetCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOffsetCode() {
		return offsetCode;
	}

	public void setOffsetCode(String offsetCode) {
		this.offsetCode = offsetCode;
	}
	
}

