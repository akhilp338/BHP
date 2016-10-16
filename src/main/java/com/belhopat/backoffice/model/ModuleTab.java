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

	@Column(name = "TAB_CODE", length = 25)
	private String tabCode;

	@Column(name = "TAB_DESC", length = 50)
	private String tabDescription;
}
