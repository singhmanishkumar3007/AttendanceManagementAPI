package com.easybusiness.attendancemanagement.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVITY_TYPE")
public class ActivityDTO {

    @Id
    @Column(name = "ACTIVITY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @Column(name = "MOD_BY")
    private String modifiedBy;

    @Column(name = "ACTIVITY_STATUS")
    private String activityStatus;

    @Column(name = "ACTIVITY_START_DATE")
    private Date activityStartDate;

    @Column(name = "ACTIVITY_END_DATE")
    private Date activityEndDate;

    @Column(name = "MOD_ON")
    private Date modifiedOn;

    public ActivityDTO() {
	super();
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }


    public String getModifiedBy() {
	return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
	return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
	this.modifiedOn = modifiedOn;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Date getActivityStartDate() {
        return activityStartDate;
    }

    public void setActivityStartDate(Date activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    public Date getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(Date activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    @Override
    public String toString() {
	return "Activity [id=" + id + ", activityName=" + activityName + ", modifiedBy=" + modifiedBy
		+ ", activityStatus=" + activityStatus + ", activityStartDate=" + activityStartDate
		+ ", activityEndDate=" + activityEndDate + ", modifiedOn=" + modifiedOn + "]";
    }


}
