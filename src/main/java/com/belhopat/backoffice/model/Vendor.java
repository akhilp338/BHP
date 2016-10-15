package com.belhopat.backoffice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VENDOR")
public class Vendor extends BaseEntity {

        @Column(name = "VNDR_CODE", length = 50)
	private String vendorCode;    
    
	@Column(name = "VNDR_NAME", length = 50)
	private String vendorName;

	@OneToOne
	@JoinColumn(name = "VNDR_ADRS")
	private Address address;

	@Column(name = "VNDR_LOC", length = 50)
	private String location;

	@ManyToOne
	@JoinColumn(name = "VNDR_CNTRY")
	private Country country;

	@ManyToOne
	@JoinColumn(name = "VNDR_CTGRY")
	private LookupDetail category;

	@ManyToOne
	@JoinColumn(name = "TDS_CTGRY")
	private LookupDetail tdsCategory;

	@Column(name = "VNDR_DESC", length = 100)
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "VNDR_PH_NO_ID")
	private Phone phoneNo;

	@Column(name = "VNDR_EMAIL", length = 50)
	private String email;

	@Column(name = "WEB_URL", length = 50)
	private String webUrl;

	@ManyToOne
	@JoinColumn(name = "VNDR_STS")
	private LookupDetail status;

	@ManyToOne
	@JoinColumn(name = "VNDR_RTNG")
	private LookupDetail rating;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PONT_OF_CONT_ID")
	private PointOfContact poc;

        public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
        
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public LookupDetail getCategory() {
		return category;
	}

	public void setCategory(LookupDetail category) {
		this.category = category;
	}

	public LookupDetail getTdsCategory() {
		return tdsCategory;
	}

	public void settdsCategory(LookupDetail tdsCategory) {
		this.tdsCategory = tdsCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Phone getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Phone phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public LookupDetail getStatus() {
		return status;
	}

	public void setStatus(LookupDetail status) {
		this.status = status;
	}

	public LookupDetail getRating() {
		return rating;
	}

	public void setRating(LookupDetail rating) {
		this.rating = rating;
	}

	public PointOfContact getPoc() {
		return poc;
	}

	public void setPoc(PointOfContact poc) {
		this.poc = poc;
	}

	@Override
	public String toString() {
		return "Vendor [vendorName=" + vendorName + ", address=" + address + ", location=" + location + ", country="
				+ country + ", category=" + category + ", TDSCategory=" + tdsCategory + ", description=" + description
				+ ", phoneNo=" + phoneNo + ", email=" + email + ", webUrl=" + webUrl + ", status=" + status
				+ ", rating=" + rating + ", poc=" + poc + "]";
	}

}
