package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class FamilyMember {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToOne
	private LookupDetail relation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LookupDetail getRelation() {
		return relation;
	}

	public void setRelation(LookupDetail relation) {
		this.relation = relation;
	}

	@Override
	public String toString() {
		return "FamilyMember [id=" + id + ", name=" + name + ", relation=" + relation + "]";
	}

}
