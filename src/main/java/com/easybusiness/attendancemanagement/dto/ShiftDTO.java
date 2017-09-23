package com.easybusiness.attendancemanagement.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHIFT_MASTER")
public class ShiftDTO {

    @Id
    @Column(name = "REC_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "SHIFT_NAME")
    private String shiftName;

    @Column(name = "WORKING_HOURS")
    private float workingHours;

    public ShiftDTO() {
	super();
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public float getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(float workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
	return "Shift [id=" + id + ", shiftName=" + shiftName + ", workingHours=" + workingHours + "]";
    }
    
    

}
