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
@Table(name = "BIOMETRIC_TRANSACTION")
public class BiometricTransactionDTO implements Serializable {

    private static final long serialVersionUID = -5300599187745769139L;

    @Id
    @Column(name = "TRAN_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transactionId;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_DEVICE_ID")
    private UserDeviceMapDTO userDeviceMap;
    
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserDTO user;

    @Column(name = "USER_DEVICE_LOCATION")
    private String userDeviceLocation;

    @Column(name = "IN_DATE")
    private Date inDate;

    @Column(name = "FIRST_IN_TIME")
    private Timestamp firstInTime;

    @Column(name = "LAST_OUT_TIME")
    private Timestamp lastOutTime;
    
    @Column(name = "TOTAL_TIME_ON_FLOOR")
    private Timestamp totalTimeOnFloor;


    public BiometricTransactionDTO() {
	super();
    }


    public Long getTransactionId() {
        return transactionId;
    }


    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }


    public UserDeviceMapDTO getUserDeviceMap() {
        return userDeviceMap;
    }


    public void setUserDeviceMap(UserDeviceMapDTO userDeviceMap) {
        this.userDeviceMap = userDeviceMap;
    }


    public UserDTO getUser() {
        return user;
    }


    public void setUser(UserDTO user) {
        this.user = user;
    }


    public String getUserDeviceLocation() {
        return userDeviceLocation;
    }


    public void setUserDeviceLocation(String userDeviceLocation) {
        this.userDeviceLocation = userDeviceLocation;
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


    @Override
    public String toString() {
	return "BiometricIntegration [transactionId=" + transactionId + ", userDeviceMap=" + userDeviceMap + ", user="
		+ user + ", userDeviceLocation=" + userDeviceLocation + ", inDate=" + inDate + ", firstInTime="
		+ firstInTime + ", lastOutTime=" + lastOutTime + ", totalTimeOnFloor=" + totalTimeOnFloor + "]";
    }


}
