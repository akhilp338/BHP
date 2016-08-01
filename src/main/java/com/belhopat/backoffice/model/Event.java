package com.belhopat.backoffice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "EVENT")
public class Event extends BaseEntity {

	@Column(name = "TITLE", length = 50)
	private String title;

	@Column(name = "IS_ALL_DAY", columnDefinition = "boolean default false", nullable = false)
	private boolean allDay;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "[START]")
	private Date start;

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "[END]")
	private Date end;

	@JsonIgnore
	@Column(name = "URL", length = 50)
	private String url;

	@JsonIgnore
	@Column(name = "CLS_NAME", length = 50)
	private String className;

	@JsonIgnore
	@Column(name = "EDITABLE", columnDefinition = "boolean default true", nullable = false)
	private boolean editable;

	@JsonIgnore
	@Column(name = "STRT_EDITABLE", columnDefinition = "boolean default true", nullable = false)
	private boolean startEditable;

	@JsonIgnore
	@Column(name = "DUR_EDITABLE", columnDefinition = "boolean default true", nullable = false)
	private boolean durationEditable;

	@JsonIgnore
	@Column(name = "OVRLAP", columnDefinition = "boolean default true", nullable = false)
	private boolean overlap;

	@JsonIgnore
	@Column(name = "[CONSTRAINT]")
	private Long constraint;

	@JsonIgnore
	@Column(name = "[SOURCE]", length = 50)
	private String source;

	@JsonIgnore
	@Column(name = "[COLOR]", length = 10)
	private String color;

	@JsonIgnore
	@Column(name = "BG_COLOR", length = 10)
	private String backgroundColor;

	@JsonIgnore
	@Column(name = "BRDR_COLOR", length = 10)
	private String borderColor;

	@JsonIgnore
	@Column(name = "TXT_COLOR", length = 10)
	private String textColor;

	@Column(name = "[DESC]", length = 100)
	private String description;

	@Column(name = "[LOCATION]", length = 50)
	private String location;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "EVENT_USER", joinColumns = { @JoinColumn(name = "EVENT_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USER_ID") })
	private List<User> guestList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isStartEditable() {
		return startEditable;
	}

	public void setStartEditable(boolean startEditable) {
		this.startEditable = startEditable;
	}

	public boolean isDurationEditable() {
		return durationEditable;
	}

	public void setDurationEditable(boolean durationEditable) {
		this.durationEditable = durationEditable;
	}

	public boolean isOverlap() {
		return overlap;
	}

	public void setOverlap(boolean overlap) {
		this.overlap = overlap;
	}

	public Long getConstraint() {
		return constraint;
	}

	public void setConstraint(Long constraint) {
		this.constraint = constraint;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<User> getGuestList() {
		return guestList;
	}

	public void setGuestList(List<User> guestList) {
		this.guestList = guestList;
	}

	@Override
	public String toString() {
		return "Event [title=" + title + ", allDay=" + allDay + ", start=" + start + ", end=" + end + ", url=" + url
				+ ", className=" + className + ", editable=" + editable + ", startEditable=" + startEditable
				+ ", durationEditable=" + durationEditable + ", overlap=" + overlap + ", constraint=" + constraint
				+ ", source=" + source + ", color=" + color + ", backgroundColor=" + backgroundColor + ", borderColor="
				+ borderColor + ", textColor=" + textColor + ", description=" + description + ", location=" + location
				+ ", guestList=" + guestList + "]";
	}
}
