package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Mst_Tasks")
public class MasterTasks {

	
	@Id
	@GeneratedValue
	private Long id;
	
	private String taskKey;
	
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

	private String taskDesc;
	
	private String taskOwnerRole;
	
	
}
