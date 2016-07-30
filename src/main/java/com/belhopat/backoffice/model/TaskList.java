package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TASK_LIST")
public class TaskList extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TSK_ID")
	private MasterTasks task;

	@Column(name = "IS_CMPLTD", columnDefinition = "boolean default false", nullable = false)
	Boolean completed;

	@Lob
	@Column(name = "[COMMENT]", length = 500)
	private String comment;

	@Column(name = "[STATUS]", length = 15)
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MasterTasks getTask() {
		return task;
	}

	public void setTask(MasterTasks task) {
		this.task = task;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "TaskList [task=" + task + ", completed=" + completed + ", comment=" + comment + ", status=" + status
				+ "]";
	}

}
