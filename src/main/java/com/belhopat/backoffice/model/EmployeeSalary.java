package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_SALARY")
public class EmployeeSalary extends BaseEntity {

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CNDT_ID")
	private Candidate candidate;

	@OneToOne(fetch = FetchType.EAGER)
	@Column(name = "GRADE_ID")
	private SalaryGrade selectedGrade;

	@Column(name = "MIN_BAS_SAL", scale = 2)
	private Double minBasicSalary;

	@Column(name = "GRS_SAL", scale = 2)
	private Double grossSalary;

	@Column(name = "MIN_FIXD_SAL", scale = 2)
	private Double minFixedSalary;

	@Column(name = "BAS_SAL", scale = 2)
	private Double basicSalary;

	@Column(name = "HRA", scale = 2)
	private Double hra;

	@Column(name = "MED_ALWNC")
	private Long medicalAllowance;

	@Column(name = "CON_ALWNC")
	private Long conveyanceAllowance;

	@Column(name = "PRF_TAX")
	private Long profTax;

	@Column(name = "PF_EMP_CNTRB")
	private Long pfEmpContrbtn;

	@Column(name = "PF_COMP_CNTRB")
	private Long pfCompContrbtn;

	@Column(name = "ESI_BY_EMPR")
	private Long esiByEmplyr;

	@Column(name = "ESI_BY_EMP")
	private Long esiByEmplye;

	@Column(name = "LEAV_ENCASH")
	private Long leaveEncash;

	@Column(name = "GRATUITY")
	private Long gratuity;

	@Column(name = "TOT_DEDUC")
	private Long totalDeductions;

	@Column(name = "FLEX_BEN_KIT")
	private Long flexyBenKit;

	@Column(name = "GRS_CTC")
	private Long grossCTC;

	@Column(name = "NET_TKHM_BFR_TDS")
	private Long netTakeHomeBeforeTDS;

	@Column(name = "[STATUS]", length = 15)
	private String status;

	@Column(name = "OFR_LTR_FL_NAME", length = 15)
	private String offerLetterFileName;

	@OneToOne
	@JoinColumn(name = "CUR_TSK_ID")
	private TaskList currentTask;

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public SalaryGrade getSelectedGrade() {
		return selectedGrade;
	}

	public void setSelectedGrade(SalaryGrade selectedGrade) {
		this.selectedGrade = selectedGrade;
	}

	public Double getMinBasicSalary() {
		return minBasicSalary;
	}

	public void setMinBasicSalary(Double minBasicSalary) {
		this.minBasicSalary = minBasicSalary;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
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

	public void setMedicalAllowance(Long medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}

	public Long getConveyanceAllowance() {
		return conveyanceAllowance;
	}

	public void setConveyanceAllowance(Long conveyanceAllowance) {
		this.conveyanceAllowance = conveyanceAllowance;
	}

	public Long getProfTax() {
		return profTax;
	}

	public void setProfTax(Long profTax) {
		this.profTax = profTax;
	}

	public Long getPfEmpContrbtn() {
		return pfEmpContrbtn;
	}

	public void setPfEmpContrbtn(Long pfEmpContrbtn) {
		this.pfEmpContrbtn = pfEmpContrbtn;
	}

	public Long getPfCompContrbtn() {
		return pfCompContrbtn;
	}

	public void setPfCompContrbtn(Long pfCompContrbtn) {
		this.pfCompContrbtn = pfCompContrbtn;
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

	public Long getTotalDeductions() {
		return totalDeductions;
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

	public Long getNetTakeHomeBeforeTDS() {
		return netTakeHomeBeforeTDS;
	}

	public void setNetTakeHomeBeforeTDS(Long netTakeHomeBeforeTDS) {
		this.netTakeHomeBeforeTDS = netTakeHomeBeforeTDS;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfferLetterFileName() {
		return offerLetterFileName;
	}

	public void setOfferLetterFileName(String offerLetterFileName) {
		this.offerLetterFileName = offerLetterFileName;
	}

	public TaskList getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(TaskList currentTask) {
		this.currentTask = currentTask;
	}

	@Override
	public String toString() {
		return "EmployeeSalary [candidate=" + candidate + ", selectedGrade=" + selectedGrade + ", minBasicSalary="
				+ minBasicSalary + ", grossSalary=" + grossSalary + ", minFixedSalary=" + minFixedSalary
				+ ", basicSalary=" + basicSalary + ", hra=" + hra + ", medicalAllowance=" + medicalAllowance
				+ ", conveyanceAllowance=" + conveyanceAllowance + ", profTax=" + profTax + ", pfEmpContrbtn="
				+ pfEmpContrbtn + ", pfCompContrbtn=" + pfCompContrbtn + ", esiByEmplyr=" + esiByEmplyr
				+ ", esiByEmplye=" + esiByEmplye + ", leaveEncash=" + leaveEncash + ", gratuity=" + gratuity
				+ ", totalDeductions=" + totalDeductions + ", flexyBenKit=" + flexyBenKit + ", grossCTC=" + grossCTC
				+ ", netTakeHomeBeforeTDS=" + netTakeHomeBeforeTDS + ", status=" + status + ", offerLetterFileName="
				+ offerLetterFileName + ", currentTask=" + currentTask + "]";
	}

}
