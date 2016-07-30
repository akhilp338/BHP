package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SALARY_GRADE")
public class SalaryGrade extends BaseEntity {

	@Column(name = "GRADE", length = 10)
	private String grade;

	@Column(name = "FIX_SAL", scale = 2)
	private Double fixedSalary;

	@Column(name = "MIN_GS_PER_MNT", scale = 2)
	private Double minGSPerMonth;

	@Column(name = "MAX_GS_PER_MNT", scale = 2)
	private Double maxGSPerMonth;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Double getFixedSalary() {
		return fixedSalary;
	}

	public void setFixedSalary(Double fixedSalary) {
		this.fixedSalary = fixedSalary;
	}

	public Double getMinGSPerMonth() {
		return minGSPerMonth;
	}

	public void setMinGSPerMonth(Double minGSPerMonth) {
		this.minGSPerMonth = minGSPerMonth;
	}

	public Double getMaxGSPerMonth() {
		return maxGSPerMonth;
	}

	public void setMaxGSPerMonth(Double maxGSPerMonth) {
		this.maxGSPerMonth = maxGSPerMonth;
	}

	@Override
	public String toString() {
		return "SalaryGrade [grade=" + grade + ", fixedSalary=" + fixedSalary + ", minGSPerMonth=" + minGSPerMonth
				+ ", maxGSPerMonth=" + maxGSPerMonth + "]";
	}

}
