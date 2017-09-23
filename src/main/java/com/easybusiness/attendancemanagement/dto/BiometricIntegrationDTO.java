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
@Table(name = "BIOMETRIC_INTEGRATION")
public class BiometricIntegrationDTO implements Serializable {

    private static final long serialVersionUID = -5300599187745769139L;

    @Id
    @Column(name = "DEVICE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long integrationId;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_DEVICE_ID")
    private UserDeviceMapDTO userDeviceMap;

    @Column(name = "USER_DEVICE_LOCATION")
    private String userDeviceLocation;

    @Column(name = "IN_DATE")
    private Date inDate;

    @Column(name = "IN_TIME")
    private Timestamp inTime;

    @Column(name = "OUT_TIME")
    private Timestamp outTime;

    @Column(name = "MOD_BY")
    private String modifiedBy;

    @Column(name = "MOD_ON")
    private Date modifiedOn;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    public BiometricIntegrationDTO() {
	super();
    }

    public Long getIntegrationId() {
	return integrationId;
    }

    public void setIntegrationId(Long integrationId) {
	this.integrationId = integrationId;
    }

    public UserDeviceMapDTO getUserDeviceMap() {
	return userDeviceMap;
    }

    public void setUserDeviceMap(UserDeviceMapDTO userDeviceMap) {
	this.userDeviceMap = userDeviceMap;
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

    public Timestamp getInTime() {
	return inTime;
    }

    public void setInTime(Timestamp inTime) {
	this.inTime = inTime;
    }

    public Timestamp getOutTime() {
	return outTime;
    }

    public void setOutTime(Timestamp outTime) {
	this.outTime = outTime;
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

    public String getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
	return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
    }

    @Override
    public String toString() {
	return "BiometricIntegration [integrationId=" + integrationId + ", userDeviceMap=" + userDeviceMap
		+ ", userDeviceLocation=" + userDeviceLocation + ", inDate=" + inDate + ", inTime=" + inTime
		+ ", outTime=" + outTime + ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn + ", createdBy="
		+ createdBy + ", createdOn=" + createdOn + "]";
    }

}
