package com.belhopat.backoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MODULE_TAB")
public class ModuleTab {

	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "TAB_NAME", length = 50)
	private String name;

	@Column(name = "TAB_LABEL", length = 50)
	private String label;

	@Column(name = "TAB_ICON", length = 50)
	private String icon;

	@Column(name = "TAB_STATE", length = 50)
	private String state;

	@Column(name = "TAB_TITLE", length = 25)
	private String title;

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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ModuleTab [id=" + id + ", name=" + name + ", label=" + label + ", icon=" + icon + ", state=" + state
				+ ", title=" + title + "]";
	}
}
