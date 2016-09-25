package com.belhopat.backoffice.dto;

/**
 * @author BHP_DEV request POJO data transfer object
 *
 */
public class TaskDTO {

	private Long taskId;

	private String comment;

	private Double amount;

	private String action;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}