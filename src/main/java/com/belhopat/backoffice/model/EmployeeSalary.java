package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employee_Salary")
public class EmployeeSalary {
	
	
	@Id
	@GeneratedValue
	@Column( name = "ID")
	private Long id;
	@OneToOne(fetch=FetchType.EAGER)
	private  Employee employee ;
	private Double minBasicSalary ;
	@OneToOne(fetch=FetchType.EAGER)
	private  SalaryGrade grade ;
	private Double minFixedSalary ;
	private  Double basicSalary ;
	private  Double hra ;
	private Long medicalAllowance ;
	private Long conveyanceAllowance ;
	private int profTax ;
	private Long pfEmpContrbtn ;
	private Long esiByEmplyr  ;
	private Long esiByEmplye  ;
	private Long leaveEncash ;
	private Long gratuity ;
	private Long totalDeductions ; 
	private Long flexyBenKit ;
	private Long grossCTC ;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Long getTotalDeductions() {
		return totalDeductions;
	}
	public Double getMinBasicSalary() {
		return minBasicSalary;
	}
	public void setMinBasicSalary(Double minBasicSalary) {
		this.minBasicSalary = minBasicSalary;
	}
	public SalaryGrade getGrade() {
		return grade;
	}
	public void setGrade(SalaryGrade grade) {
		this.grade = grade;
	}
	public Double getMinFixedSalary() {
		return minFixedSalary;
	}
	public void setMinFixedSalary(Double minFixedSalary) {
		this.minFixedSalary = minFixedSalary;
	}
	public Double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public Double getHra() {
		return hra;
	}
	public void setHra(Double hra) {
		this.hra = hra;
	}
	public Long getMedicalAllowance() {
		return medicalAllowance;
	}
	public void setMedicalAllowance(Long medicalAllowanceLong) {
		this.medicalAllowance = medicalAllowanceLong;
	}
	public Long getConveyanceAllowance() {
		return conveyanceAllowance;
	}
	public void setConveyanceAllowance(Long conveyanceAllowanceLong) {
		this.conveyanceAllowance = conveyanceAllowanceLong;
	}
	public int getProfTax() {
		return profTax;
	}
	public void setProfTax(int profTax) {
		this.profTax = profTax;
	}
	public Long getPfEmpContrbtn() {
		return pfEmpContrbtn;
	}
	public void setPfEmpContrbtn(Long pfEmpContrbtn) {
		this.pfEmpContrbtn = pfEmpContrbtn;
	}
	public Long getEsiByEmplyr() {
		return esiByEmplyr;
	}
	public void setEsiByEmplyr(Long esiByEmplyr) {
		this.esiByEmplyr = esiByEmplyr;
	}
	public Long getEsiByEmplye() {
		return esiByEmplye;
	}
	public void setEsiByEmplye(Long esiByEmplye) {
		this.esiByEmplye = esiByEmplye;
	}
	public Long getLeaveEncash() {
		return leaveEncash;
	}
	public void setLeaveEncash(Long leaveEncash) {
		this.leaveEncash = leaveEncash;
	}
	public Long getGratuity() {
		return gratuity;
	}
	public void setGratuity(Long gratuity) {
		this.gratuity = gratuity;
	}
	public void setTotalDeductions(Long totalDeductions) {
		this.totalDeductions = totalDeductions;
	}
	public Long getFlexyBenKit() {
		return flexyBenKit;
	}
	public void setFlexyBenKit(Long flexyBenKit) {
		this.flexyBenKit = flexyBenKit;
	}
	public Long getGrossCTC() {
		return grossCTC;
	}
	public void setGrossCTC(Long grossCTC) {
		this.grossCTC = grossCTC;
	} 

}
