package com.belhopat.backoffice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "ATTENDANCE" )
public class Attendance extends BaseEntity {

    @Column( name = "DATE" )
    private Date date;

    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "EMP_ID" )
    private Employee employee;

    @Column( name = "IN_TIME" )
    private String inTime;

    @Column( name = "OUT_TIME" )
    private String outTime;

    @Column( name = "LATE_MINS" )
    private Integer lateMinutes;

    @Column( name = "EARLY_DEP_MINS" )
    private Integer earlyMinutes;

    @Column( name = "WRK_HRS" )
    private String workHours;

    @Column( name = "ATND_STS" )
    private String status;

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee( Employee employee ) {
        this.employee = employee;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime( String inTime ) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime( String outTime ) {
        this.outTime = outTime;
    }

    public Integer getLateMinutes() {
        return lateMinutes;
    }

    public void setLateMinutes( Integer lateMinutes ) {
        this.lateMinutes = lateMinutes;
    }

    public Integer getEarlyMinutes() {
        return earlyMinutes;
    }

    public void setEarlyMinutes( Integer earlyMinutes ) {
        this.earlyMinutes = earlyMinutes;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours( String workHours ) {
        this.workHours = workHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Attendance [date=" + date + ", employee=" + employee + ", inTime=" + inTime
            + ", outTime=" + outTime + ", lateMinutes=" + lateMinutes + ", earlyMinutes="
            + earlyMinutes + ", workHours=" + workHours + ", status=" + status + "]";
    }
}
