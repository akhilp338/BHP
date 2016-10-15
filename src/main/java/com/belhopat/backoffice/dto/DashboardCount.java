package com.belhopat.backoffice.dto;

public class DashboardCount {

	private Integer completed;

	private Integer critical;

	private Integer nonCritical;

	private Integer mediumCritical;

	public Integer getCompleted() {
		return completed;
	}

	public void setCompleted(Integer completed) {
		this.completed = completed;
	}

	public Integer getCritical() {
		return critical;
	}

	public void setCritical(Integer critical) {
		this.critical = critical;
	}

	public Integer getNonCritical() {
		return nonCritical;
	}

	public void setNonCritical(Integer nonCritical) {
		this.nonCritical = nonCritical;
	}

	public Integer getMediumCritical() {
		return mediumCritical;
	}

	public void setMediumCritical(Integer mediumCritical) {
		this.mediumCritical = mediumCritical;
	}

	@Override
	public String toString() {
		return "DashboardCount [completed=" + completed + ", critical=" + critical + ", nonCritical=" + nonCritical
				+ ", mediumCritical=" + mediumCritical + "]";
	}

}
