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

}
