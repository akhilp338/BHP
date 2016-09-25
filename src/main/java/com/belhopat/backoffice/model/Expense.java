package com.belhopat.backoffice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EXPENSE")
public class Expense {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "EXP_DATE")
	private Date date;

	@Column(name = "EXP_DESC", length = 100)
	private String description;

	@ManyToOne
	@JoinColumn(name = "CURR_ID")
	private Currency currency;

	@Column(name = "EXP_AMNT", scale = 2)
	private Double amount;

	@Column(name = "EXP_RMRKS", length = 100)
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", date=" + date + ", description=" + description + ", currency=" + currency
				+ ", amount=" + amount + ", remarks=" + remarks + "]";
	}
}
