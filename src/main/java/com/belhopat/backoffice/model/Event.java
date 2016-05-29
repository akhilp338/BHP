package com.belhopat.backoffice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Event extends BaseEntity {

	private String name;

	private String description;

	private Date fromDate;

	private Date toDate;

	@ManyToOne
	private TimeZone timeZone;

	private String location;

	@ManyToOne
	private LookupDetail recurFrequency;

	@ManyToMany
	private List<LookupDetail> recurDays;

	private Integer recurInterval;

	private Date recurEndDate;

	private Integer noOfOccurrences;

	@ManyToMany
	private List<Employee> guests;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LookupDetail getRecurFrequency() {
		return recurFrequency;
	}

	public void setRecurFrequency(LookupDetail recurFrequency) {
		this.recurFrequency = recurFrequency;
	}

	public List<LookupDetail> getRecurDays() {
		return recurDays;
	}

	public void setRecurDays(List<LookupDetail> recurDays) {
		this.recurDays = recurDays;
	}

	public Integer getRecurInterval() {
		return recurInterval;
	}

	public void setRecurInterval(Integer recurInterval) {
		this.recurInterval = recurInterval;
	}

	public Date getRecurEndDate() {
		return recurEndDate;
	}

	public void setRecurEndDate(Date recurEndDate) {
		this.recurEndDate = recurEndDate;
	}

	public Integer getNoOfOccurrences() {
		return noOfOccurrences;
	}

	public void setNoOfOccurrences(Integer noOfOccurrences) {
		this.noOfOccurrences = noOfOccurrences;
	}

	public List<Employee> getGuests() {
		return guests;
	}

	public void setGuests(List<Employee> guests) {
		this.guests = guests;
	}

}
