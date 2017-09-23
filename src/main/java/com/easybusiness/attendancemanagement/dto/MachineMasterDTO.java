package com.easybusiness.attendancemanagement.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MACHINEMASTER")
public class MachineMasterDTO {

    @Id
    @Column(name = "MACHINE_CODE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String machineCode;

    @Column(name = "MACHINE_DESC")
    private String machineDesc;

    @Column(name = "MILL_FACT")
    private char millFact;
    
    @Column(name = "USER_1")
    private String user1;
    
    @Column(name = "USER_2")
    private String user2;
    
    @Column(name = "USER_3")
    private String user3;
    
    @Column(name = "USER_4")
    private String user4;
    
    @Column(name = "DATE_1")
    private Date date1;
    
    @Column(name = "DATE_2")
    private Date date2;
    
    @Column(name = "DATE_3")
    private Date date3;
    
    @Column(name = "DATE_4")
    private Date date4;
    
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    
    @Column(name = "DEPT")
    private String dept;

    public MachineMasterDTO() {
	super();
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getMachineDesc() {
        return machineDesc;
    }

    public void setMachineDesc(String machineDesc) {
        this.machineDesc = machineDesc;
    }

    public char getMillFact() {
        return millFact;
    }

    public void setMillFact(char millFact) {
        this.millFact = millFact;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getUser3() {
        return user3;
    }

    public void setUser3(String user3) {
        this.user3 = user3;
    }

    public String getUser4() {
        return user4;
    }

    public void setUser4(String user4) {
        this.user4 = user4;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public Date getDate4() {
        return date4;
    }

    public void setDate4(Date date4) {
        this.date4 = date4;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
	return "MachineMaster [machineCode=" + machineCode + ", machineDesc=" + machineDesc + ", millFact=" + millFact
		+ ", user1=" + user1 + ", user2=" + user2 + ", user3=" + user3 + ", user4=" + user4 + ", date1=" + date1
		+ ", date2=" + date2 + ", date3=" + date3 + ", date4=" + date4 + ", activeFlag=" + activeFlag
		+ ", dept=" + dept + "]";
    }

    
    

}
