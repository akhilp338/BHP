package com.belhopat.backoffice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "REIMBURSE")
public class Reimburse extends BaseEntity {

	@Column(name = "REIM_REF_ID", length = 20)
	private String reimburseId;

	@ManyToOne
	@JoinColumn(name = "EMP_ID")
	private Employee employee;

	@Column(name = "ELG_AMNT")
	private Double eligibleAmount;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Expense> expenses;

	public String getReimburseId() {
		return reimburseId;
	}

	public void setReimburseId(String reimburseId) {
		this.reimburseId = reimburseId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Double getEligibleAmount() {
		return eligibleAmount;
	}

	public void setEligibleAmount(Double eligibleAmount) {
		this.eligibleAmount = eligibleAmount;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
}
