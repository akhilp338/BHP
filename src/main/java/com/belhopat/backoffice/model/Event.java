package com.belhopat.backoffice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Event")
public class Event extends BaseEntity {

	private String title;

	private boolean allDay;

	@Column(name = "[start]")
	private Date start;

	@Column(name = "[end]")
	private Date end;

	@JsonIgnore
	private String url;

	@JsonIgnore
	private String className;

	@JsonIgnore
	private boolean editable;

	@JsonIgnore
	private boolean startEditable;

	@JsonIgnore
	private boolean durationEditable;

	@JsonIgnore
	private String rendering;

	@JsonIgnore
	private boolean overlap;

	@JsonIgnore
	@Column(name = "[constraint]")
	private Long constraint;

	@JsonIgnore
	@Column(name = "[source]")
	private String source;

	@JsonIgnore
	private String color;

	@JsonIgnore
	private String backgroundColor;

	@JsonIgnore
	private String borderColor;

	@JsonIgnore
	private String textColor;

	private String description;

	private String location;

	@ManyToMany(fetch = FetchType.EAGER)
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

	public String getRendering() {
		return rendering;
	}

	public void setRendering(String rendering) {
		this.rendering = rendering;
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

	public void addGuest(User guest) {
		this.guestList.add(guest);
	}

	public void removeGuest(Employee guest) {
		this.guestList.remove(guest);
	}

}
