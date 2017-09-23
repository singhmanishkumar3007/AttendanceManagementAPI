package com.easybusiness.attendancemanagement.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_EFFORT_DETAILS")
public class UserDeviceEffortDTO implements Serializable {

    private static final long serialVersionUID = -5300599187745769139L;

    @Id
    @Column(name = "EFFORT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTIVITY_ID")
    private ActivityDTO activity;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserDTO user;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "TASK_ID")
    private TaskDTO task;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTENDANCE_ID")
    private BiometricTransactionDTO biometricTransaction;

    @Column(name = "EFFORT_DATE")
    private Date effortDate;

    @Column(name = "TIME_IN_CAMPUS")
    private Timestamp timeInCampus;

    @Column(name = "TIME_IN_FLOOR")
    private Timestamp timeInFloor;

    @Column(name = "TIME_OUTSIDE_FLOOR")
    private Timestamp timeOutsideFloor;

    @Column(name = "TOTAL_EFFORT")
    private String totalEffort;

    @Column(name = "EFFORT_PER_TASK")
    private String actualEffortPerTask;

    @Column(name = "MOD_BY")
    private String modifiedBy;

    @Column(name = "MOD_ON")
    private Date modifiedOn;

    public UserDeviceEffortDTO() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public ActivityDTO getActivity() {
	return activity;
    }

    public void setActivity(ActivityDTO activity) {
	this.activity = activity;
    }


    public TaskDTO getTask() {
	return task;
    }

    public void setTask(TaskDTO task) {
	this.task = task;
    }

    public Date getEffortDate() {
	return effortDate;
    }

    public void setEffortDate(Date effortDate) {
	this.effortDate = effortDate;
    }


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BiometricTransactionDTO getBiometricTransaction() {
        return biometricTransaction;
    }

    public void setBiometricTransaction(BiometricTransactionDTO biometricTransaction) {
        this.biometricTransaction = biometricTransaction;
    }

    public Timestamp getTimeInCampus() {
	return timeInCampus;
    }

    public void setTimeInCampus(Timestamp timeInCampus) {
	this.timeInCampus = timeInCampus;
    }

    public Timestamp getTimeInFloor() {
	return timeInFloor;
    }

    public void setTimeInFloor(Timestamp timeInFloor) {
	this.timeInFloor = timeInFloor;
    }

    public Timestamp getTimeOutsideFloor() {
	return timeOutsideFloor;
    }

    public void setTimeOutsideFloor(Timestamp timeOutsideFloor) {
	this.timeOutsideFloor = timeOutsideFloor;
    }

    public String getTotalEffort() {
	return totalEffort;
    }

    public void setTotalEffort(String totalEffort) {
	this.totalEffort = totalEffort;
    }

    public String getActualEffortPerTask() {
	return actualEffortPerTask;
    }

    public void setActualEffortPerTask(String actualEffortPerTask) {
	this.actualEffortPerTask = actualEffortPerTask;
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

    @Override
    public String toString() {
	return "UserDeviceEffort [id=" + id + ", activity=" + activity + ", user=" + user + ", task=" + task
		+ ", biometricTransaction=" + biometricTransaction + ", effortDate=" + effortDate + ", timeInCampus="
		+ timeInCampus + ", timeInFloor=" + timeInFloor + ", timeOutsideFloor=" + timeOutsideFloor
		+ ", totalEffort=" + totalEffort + ", actualEffortPerTask=" + actualEffortPerTask + ", modifiedBy="
		+ modifiedBy + ", modifiedOn=" + modifiedOn + "]";
    }

}
