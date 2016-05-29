package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Salary_Grade")
public class SalaryGrade extends BaseEntity {
	
	private String grade;
	
	private Double fixedSalary;
	
	private Double minGSPerMonth;
	
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
	
}
