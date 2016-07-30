package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SKILL")
public class Skill {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "SKIL_NAME", length = 25)
	private String skillName;

	@Column(name = "PRIR_EXP_YR")
	public Integer experienceYear;

	@Column(name = "PRIR_EXP_MNT")
	public Integer experienceMonth;

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

	public Integer getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(Integer experienceYear) {
		this.experienceYear = experienceYear;
	}

	public Integer getExperienceMonth() {
		return experienceMonth;
	}

	public void setExperienceMonth(Integer experienceMonth) {
		this.experienceMonth = experienceMonth;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", skillName=" + skillName + ", experienceYear=" + experienceYear
				+ ", experienceMonth=" + experienceMonth + "]";
	}

}