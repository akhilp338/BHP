package com.belhopat.backoffice.dto;

public class SalaryDTO {
	
	private Double annualCTC;
	
	private String grade;

	private Double grossSalary;
	
	public Double getAnnualCTC() {
		return annualCTC;
	}

	public void setAnnualCTC(Double annualCTC) {
		this.annualCTC = annualCTC;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}
	

}
