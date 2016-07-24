package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Skill {

	@Id
	@GeneratedValue
	private Long id;

	private String skillName;

	public Long experienceYear;

	public Long experienceMonth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Long getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(Long experienceYear) {
		this.experienceYear = experienceYear;
	}

	public Long getExperienceMonth() {
		return experienceMonth;
	}

	public void setExperienceMonth(Long experienceMonth) {
		this.experienceMonth = experienceMonth;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", skillName=" + skillName + ", experienceYear=" + experienceYear
				+ ", experienceMonth=" + experienceMonth + "]";
	}

}