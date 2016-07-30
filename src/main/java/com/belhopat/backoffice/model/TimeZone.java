package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIME_ZONE")
public class TimeZone {

	@GeneratedValue
	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "UTC_OFFSET", length = 10)
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

	@Override
	public String toString() {
		return "TimeZone [id=" + id + ", offsetCode=" + offsetCode + "]";
	}

}
