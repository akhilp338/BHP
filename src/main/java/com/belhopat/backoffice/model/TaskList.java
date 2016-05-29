package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "Task_List")
public class TaskList extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "task_id")
	private MasterTasks task;

	@Column(name = "completed")
	byte completed;
	
	@Lob
	@NotAudited
	@Column(name = "comment")
	private String comment;
	
	
	@Column(name = "status")
	private String status;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nextTaskId")
	private MasterTasks nextTask;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MasterTasks getNextTask() {
		return nextTask;
	}

	public void setNextTask(MasterTasks nextTask) {
		this.nextTask = nextTask;
	}

	public MasterTasks getTask() {
		return task;
	}

	public void setTask(MasterTasks task) {
		this.task = task;
	}

	public byte getCompleted() {
		return completed;
	}

	public void setCompleted(byte completed) {
		this.completed = completed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
