package com.easybusiness.attendancemanagement.dto;

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

@Entity()
@Table(name = "DEPARTMENT")
public class DepartmentDTO {

    @Id
    @Column(name = "DEPT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORG_ID")
    private OrganizationDTO organization;

    @Column(name = "DEPT_NAME")
    private String deptName;

    public DepartmentDTO() {
	super();
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public OrganizationDTO getOrganization() {
	return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
	this.organization = organization;
    }

    public String getDeptName() {
	return deptName;
    }

    public void setDeptName(String deptName) {
	this.deptName = deptName;
    }

    @Override
    public String toString() {
	return "Department [id=" + id + ", organization=" + organization + ", deptName=" + deptName + "]";
    }

}
