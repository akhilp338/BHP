package com.belhopat.backoffice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity implements Cloneable {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRTD_DATE")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPTD_DATE")
	private Date updatedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DLTD_DATE")
	private Date deletedDate;

	@ManyToOne
	@JoinColumn(name = "CRTD_BY_ID")
	private User createdBy;

	@ManyToOne
	@JoinColumn(name = "UPTD_BY_ID")
	private User updatedBy;

	@ManyToOne
	@JoinColumn(name = "DLTD_BY_ID")
	private User deletedBy;

	@Column(name = "IS_DLTD", columnDefinition = "boolean default false", nullable = false)
	private boolean deleted;

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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public User getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(User deletedBy) {
		this.deletedBy = deletedBy;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setBaseAttributes(User user) {
		this.createdBy = user;
		this.createdDate = new Date();
		this.updatedBy = user;
		this.updatedDate = new Date();
	}

	public void setUpdateAttributes(User user) {
		this.updatedBy = user;
		this.updatedDate = new Date();
	}

	public void setDeleteAttributes(User user) {
		this.updatedBy = user;
		this.updatedDate = new Date();
		this.deleted = true;
		this.deletedBy = user;
		this.deletedDate = new Date();
	}

	public void hideAttributes() {
		this.id = null;
		this.createdBy = null;
		this.createdDate = null;
		this.updatedBy = null;
		this.updatedDate = null;
		this.deletedBy = null;
		this.deletedDate = null;
	}
}