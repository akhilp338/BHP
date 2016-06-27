package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Mst_Tasks")
public class MasterTasks {

	
	@Id
	@GeneratedValue
	private Long id;
	
	private String taskKey;
	
	private Long nextTaskId;
	
	private String taskDesc;
	
	private String taskOwnerRole;
	
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
	
}
