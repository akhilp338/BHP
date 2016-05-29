package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Skill {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private LookupDetail type;
	
	private String skillName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LookupDetail getType() {
		return type;
	}

	public void setType(LookupDetail type) {
		this.type = type;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", type=" + type + ", skillName=" + skillName + "]";
	}
	
}
