package com.easybusiness.attendancemanagement.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class SaveOrEditBiometricTransactionRequestBody {

    private String userId;

    private Date inDate;

    private Timestamp firstInTime;

    private Timestamp lastOutTime;

    private Timestamp totalTimeOnFloor;

    private String userDeviceLocation;

    public SaveOrEditBiometricTransactionRequestBody() {
	super();
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public Date getInDate() {
	return inDate;
    }

    public void setInDate(Date inDate) {
	this.inDate = inDate;
    }

    public Timestamp getFirstInTime() {
	return firstInTime;
    }

    public void setFirstInTime(Timestamp firstInTime) {
	this.firstInTime = firstInTime;
    }

    public Timestamp getLastOutTime() {
	return lastOutTime;
    }

    public void setLastOutTime(Timestamp lastOutTime) {
	this.lastOutTime = lastOutTime;
    }

    public Timestamp getTotalTimeOnFloor() {
	return totalTimeOnFloor;
    }

    public void setTotalTimeOnFloor(Timestamp totalTimeOnFloor) {
	this.totalTimeOnFloor = totalTimeOnFloor;
    }

    public String getUserDeviceLocation() {
	return userDeviceLocation;
    }

    public void setUserDeviceLocation(String userDeviceLocation) {
	this.userDeviceLocation = userDeviceLocation;
    }

    @Override
    public String toString() {
	return "SaveOrEditBiometricTransactionRequestBody [userId=" + userId + ", inDate=" + inDate + ", firstInTime="
		+ firstInTime + ", lastOutTime=" + lastOutTime + ", totalTimeOnFloor=" + totalTimeOnFloor
		+ ", userDeviceLocation=" + userDeviceLocation + "]";
    }

}
