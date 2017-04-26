package com.belhopat.backoffice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MST_ROLE")
public class MasterRole {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "ROLE_NAME", length = 10)
	private String roleName;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ROLE_MODULE_TAB", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "MODULE_TAB_ID") })
	private List<ModuleTab> moduleTabs = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<ModuleTab> getModuleTabs() {
		return moduleTabs;
	}

	public void setModuleTabs(List<ModuleTab> moduleTabs) {
		this.moduleTabs = moduleTabs;
	}

	@Override
	public String toString() {
		return "MasterRole [id=" + id + ", roleName=" + roleName + ", moduleTabs=" + moduleTabs + "]";
	}
}
