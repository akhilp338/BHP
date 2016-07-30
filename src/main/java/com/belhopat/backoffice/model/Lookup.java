package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOOKUP")
public class Lookup {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "LOOKUP_KEY", unique = true, nullable = false, length = 15)
	private String lookupKey;

	@Column(name = "DESC", length = 50)
	private String decription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLookupKey() {
		return lookupKey;
	}

	public void setLookupKey(String lookupKey) {
		this.lookupKey = lookupKey;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	@Override
	public String toString() {
		return "Lookup [id=" + id + ", lookupKey=" + lookupKey + ", decription=" + decription + "]";
	}

}
