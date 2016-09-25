package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class Task extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TSK_ID")
	private MasterTask masterTask;

	@Column(name = "IS_CMPLTD", columnDefinition = "boolean default false", nullable = false)
	private Boolean completed;

	@Column(name = "TSK_ENT_ID")
	private Long taskEntityId;

	@Lob
	@Column(name = "[COMMENT]", length = 500)
	private String comment;

	@Column(name = "[STATUS]", length = 15)
	private String status;

	@Column(name = "ADTNL_FLD_1", length = 100)
	private String additionalField1;

	@Column(name = "ADTNL_FLD_2", length = 100)
	private String additionalField2;

	public MasterTask getMasterTask() {
		return masterTask;
	}

	public void setMasterTask(MasterTask masterTask) {
		this.masterTask = masterTask;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Long getTaskEntityId() {
		return taskEntityId;
	}

	public void setTaskEntityId(Long taskEntityId) {
		this.taskEntityId = taskEntityId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdditionalField1() {
		return additionalField1;
	}

	public void setAdditionalField1(String additionalField1) {
		this.additionalField1 = additionalField1;
	}

	public String getAdditionalField2() {
		return additionalField2;
	}

	public void setAdditionalField2(String additionalField2) {
		this.additionalField2 = additionalField2;
	}

	@Override
	public String toString() {
		return "Task [masterTask=" + masterTask + ", completed=" + completed + ", taskEntityId=" + taskEntityId
				+ ", comment=" + comment + ", status=" + status + ", additionalField1=" + additionalField1
				+ ", additionalField2=" + additionalField2 + "]";
	}
}
