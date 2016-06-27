package com.belhopat.backoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class RoleTab {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JsonIgnore
	private MasterRoles masterRole;

	private String tabCode;

	private String tabDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MasterRoles getMasterRole() {
		return masterRole;
	}

	public void setMasterRole(MasterRoles masterRole) {
		this.masterRole = masterRole;
	}

	public String getTabCode() {
		return tabCode;
	}

	public void setTabCode(String tabCode) {
		this.tabCode = tabCode;
	}

	public String getTabDescription() {
		return tabDescription;
	}

	public void setTabDescription(String tabDescription) {
		this.tabDescription = tabDescription;
	}

	@Override
	public String toString() {
		return "RoleTab [id=" + id + ", masterRole=" + masterRole + ", tabCode=" + tabCode + ", tabDescription="
				+ tabDescription + "]";
	}

}
