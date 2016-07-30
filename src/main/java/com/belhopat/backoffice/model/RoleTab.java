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
@Table(name = "ROLE_TAB")
public class RoleTab {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "MST_ROLE_ID")
	private MasterRole masterRole;

	@Column(name = "TAB_CODE", length = 25)
	private String tabCode;

	@Column(name = "TAB_DESC", length = 50)
	private String tabDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MasterRole getMasterRole() {
		return masterRole;
	}

	public void setMasterRole(MasterRole masterRole) {
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
