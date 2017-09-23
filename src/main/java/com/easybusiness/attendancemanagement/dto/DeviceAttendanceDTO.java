package com.easybusiness.attendancemanagement.dto;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "DEVICE_ATTENDANCE_DATA")
public class DeviceAttendanceDTO implements Serializable {

    private static final long serialVersionUID = -5300599187745769139L;

    @Id
    @Column(name = "ENTRY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserDTO user;

    @Column(name = "ATTENDANCE_DATE")
    private Date attendanceDate;

    @Column(name = "IN_TIME")
    private String inTime;

    @Column(name = "OUT_TIME")
    private String outTime;

    public DeviceAttendanceDTO() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public UserDTO getUser() {
	return user;
    }

    public void setUser(UserDTO user) {
	this.user = user;
    }

    public Date getAttendanceDate() {
	return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
	this.attendanceDate = attendanceDate;
    }

    public String getInTime() {
	return inTime;
    }

    public void setInTime(String inTime) {
	this.inTime = inTime;
    }

    public String getOutTime() {
	return outTime;
    }

    public void setOutTime(String outTime) {
	this.outTime = outTime;
    }

    @Override
    public String toString() {
	return "DeviceAttendanceData [id=" + id + ", user=" + user + ", attendanceDate=" + attendanceDate + ", inTime="
		+ inTime + ", outTime=" + outTime + "]";
    }

}
