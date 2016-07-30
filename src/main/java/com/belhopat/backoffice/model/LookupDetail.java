package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LOOKUP_DTLS")
public class LookupDetail {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "LOOKUP_ID")
	private Lookup lookup;

	@Column(name = "CODE", nullable = false, length = 10)
	private String code;

	@Column(name = "DESC", nullable = false, length = 50)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lookup getLookup() {
		return lookup;
	}

	public void setLookup(Lookup lookup) {
		this.lookup = lookup;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Lookup [id=" + id + ", lookup=" + lookup + ", code=" + code + ", description=" + description + "]";
	}

}
