package com.belhopat.backoffice.dto;

public class DashboardCount {

	private Long completed;

	private Long pending;

	private Long critical;

	private Long nonCritical;

	private Long mediumCritical;

	public Long getCompleted() {
		return completed;
	}

	public void setCompleted(Long completed) {
		this.completed = completed;
	}

	public Long getPending() {
		return pending;
	}

	public void setPending(Long pending) {
		this.pending = pending;
	}

	public Long getCritical() {
		return critical;
	}

	public void setCritical(Long critical) {
		this.critical = critical;
	}

	public Long getNonCritical() {
		return nonCritical;
	}

	public void setNonCritical(Long nonCritical) {
		this.nonCritical = nonCritical;
	}

	public Long getMediumCritical() {
		return mediumCritical;
	}

	public void setMediumCritical(Long mediumCritical) {
		this.mediumCritical = mediumCritical;
	}

	@Override
	public String toString() {
		return "DashboardCount [completed=" + completed + ", pending=" + pending + ", critical=" + critical
				+ ", nonCritical=" + nonCritical + ", mediumCritical=" + mediumCritical + "]";
	}

}
