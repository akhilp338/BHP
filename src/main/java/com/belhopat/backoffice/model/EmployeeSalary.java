package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employee_Salary")
public class EmployeeSalary  extends BaseEntity{
	
	
	/*@Id
	@GeneratedValue
	@Column( name = "ID")
	private Long id;*/
	@OneToOne(fetch=FetchType.EAGER)
	private  Candidate candidate ;
	private Double minBasicSalary ;
	@OneToOne(fetch=FetchType.EAGER)
	private  SalaryGrade selectedGrade ;
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
	private String status ;
	@OneToOne
	private TaskList currentTask;
	
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
		return selectedGrade;
	}
	public void setGrade(SalaryGrade grade) {
		this.selectedGrade = grade;
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
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TaskList getCurrentTask() {
		return currentTask;
	}
	public void setCurrentTask(TaskList currentTask) {
		this.currentTask = currentTask;
	}
	public SalaryGrade getSelectedGrade() {
		return selectedGrade;
	}
	public void setSelectedGrade(SalaryGrade selectedGrade) {
		this.selectedGrade = selectedGrade;
	}

}
