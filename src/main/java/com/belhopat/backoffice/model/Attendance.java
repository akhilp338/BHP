package com.belhopat.backoffice.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ATTENDANCE")
public class Attendance extends BaseEntity {

	@Column(name = "DATE")
	private Date date;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMP_ID")
	private Employee employee;

	@Column(name = "IN_TIME")
	private Date inTime;

	@Column(name = "OUT_TIME")
	private Date outTime;

	@Column(name = "LATE_MINS")
	private Integer lateMinutes;

	@Column(name = "EARLY_DEP_MINS")
	private Integer earlyMinutes;

	@Column(name = "WRK_HRS")
	private Time workHours;

	@Column(name = "ATND_STS")
	private String status;
}
