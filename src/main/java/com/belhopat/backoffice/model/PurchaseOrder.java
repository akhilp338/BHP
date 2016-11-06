package com.belhopat.backoffice.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder extends BaseEntity {

	@Column(name = "PURCHSE_ORDR_NO", length = 50)
	private String purchaseOrderNo;

	@Column(name = "PO_DATE")
	private Date poDate;

	@Column(name = "CURNCY")
	private String currency;

	@Column(name = "MNTHLY_RTE")
	private Double monthlyRate;

	@Column(name = "PO_VALUE")
	private BigDecimal poValue;

	@Column(name = "EXPRY_DATE")
	private Date expiry;

	@Column(name = "STATUS", length = 100)
	private String status;

	@Column(name = "VNDR_NAME", length = 100)
	private String vendorName;

	@Column(name = "PO_DESC", length = 200)
	private String poDesc;

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		currency = currency;
	}

	public Double getMonthlyRate() {
		return monthlyRate;
	}

	public void setMonthlyRate(Double monthlyRate) {
		this.monthlyRate = monthlyRate;
	}

	public BigDecimal getPoValue() {
		return poValue;
	}

	public void setPoValue(BigDecimal poValue) {
		this.poValue = poValue;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPoDesc() {
		return poDesc;
	}

	public void setPoDesc(String poDesc) {
		this.poDesc = poDesc;
	}

}
