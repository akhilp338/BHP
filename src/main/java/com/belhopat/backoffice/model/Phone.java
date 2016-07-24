package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.jmx.export.annotation.ManagedOperationParameter;

@Entity
@Table
public class Phone {

	@Id
	@GeneratedValue
	private Long id;

	private String number;

	@ManyToOne
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", number=" + number + ", country=" + country + "]";
	}

}
