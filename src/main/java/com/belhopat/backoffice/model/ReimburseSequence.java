package com.belhopat.backoffice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REIMBURSE_SEQUENCE")
public class ReimburseSequence {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRTD_DATE", nullable = false)
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ReimburseSequence() {
		super();
		this.createdDate = new Date();
	}
}