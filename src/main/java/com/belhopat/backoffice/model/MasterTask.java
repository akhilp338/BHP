package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MST_TASK")
public class MasterTask {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "TSK_KEY", length = 50)
	private String taskKey;

	@Column(name = "NXT_TSK_ID")
	private Long nextTaskId;

	@Column(name = "TSK_DESC", length = 50)
	private String taskDesc;

	@Column(name = "TSK_OWNR_ROLE", length = 10)
	private String taskOwnerRole;

	@Column(name = "TSK_ROUTE", length = 30)
	private String taskRoute;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskOwnerRole() {
		return taskOwnerRole;
	}

	public void setTaskOwnerRole(String taskOwnerRole) {
		this.taskOwnerRole = taskOwnerRole;
	}

	public Long getNextTaskId() {
		return nextTaskId;
	}

	public void setNextTaskId(Long nextTaskId) {
		this.nextTaskId = nextTaskId;
	}

	public String getTaskRoute() {
		return taskRoute;
	}

	public void setTaskRoute(String taskRoute) {
		this.taskRoute = taskRoute;
	}

	@Override
	public String toString() {
		return "MasterTasks [id=" + id + ", taskKey=" + taskKey + ", nextTaskId=" + nextTaskId + ", taskDesc="
				+ taskDesc + ", taskOwnerRole=" + taskOwnerRole + ", taskRoute=" + taskRoute + "]";
	}
}
