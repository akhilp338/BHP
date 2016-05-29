package com.belhopat.backoffice.dto;

import java.util.Date;

import com.belhopat.backoffice.model.Event;
import com.belhopat.backoffice.model.LookupDetail;

/**
 * @author BHP_DEV request POJO data repeat event object
 *
 */
public class EventDTO {

	private Event event;

	private boolean repeatStatus;

	private Date repeatStartDate;

	private Date repeatEndDate;

	private Integer noOfRepeatOccurrences;

	private LookupDetail repeatsBy;

	private Integer repeatFrequency;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public boolean getRepeatStatus() {
		return repeatStatus;
	}

	public void setRepeatStatus(boolean repeatStatus) {
		this.repeatStatus = repeatStatus;
	}

	public Date getRepeatStartDate() {
		return repeatStartDate;
	}

	public void setRepeatStartDate(Date repeatStartDate) {
		this.repeatStartDate = repeatStartDate;
	}

	public Date getRepeatEndDate() {
		return repeatEndDate;
	}

	public void setRepeatEndDate(Date repeatEndDate) {
		this.repeatEndDate = repeatEndDate;
	}

	public Integer getNoOfRepeatOccurrences() {
		return noOfRepeatOccurrences;
	}

	public void setNoOfRepeatOccurrences(Integer noOfRepeatOccurrences) {
		this.noOfRepeatOccurrences = noOfRepeatOccurrences;
	}

	public LookupDetail getRepeatsBy() {
		return repeatsBy;
	}

	public void setRepeatsBy(LookupDetail repeatsBy) {
		this.repeatsBy = repeatsBy;
	}

	public Integer getRepeatFrequency() {
		return repeatFrequency;
	}

	public void setRepeatFrequency(Integer repeatFrequency) {
		this.repeatFrequency = repeatFrequency;
	}

	@Override
	public String toString() {
		return "EventDTO [event=" + event + ", repeatStatus=" + repeatStatus + ", repeatStartDate=" + repeatStartDate
				+ ", repeatEndDate=" + repeatEndDate + ", noOfRepeatOccurrences=" + noOfRepeatOccurrences
				+ ", repeatsBy=" + repeatsBy + ", repeatFrequency=" + repeatFrequency + "]";
	}

}