package com.easybusiness.attendancemanagement.services.employeemaster;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.easybusiness.attendancepersistence.entity.EmployeeMaster;

public interface EmployeeMasterService {

    public ResponseEntity<EmployeeMaster> getEmployeeMasterByEbId(String ebId);

    public ResponseEntity<EmployeeMaster> getEmployeeMasterByEbFixedId(String ebFixedId);
    
    public ResponseEntity<List<EmployeeMaster>> getEmployeeMasterByUserId(String userId);


    public ResponseEntity<List<EmployeeMaster>> getAllFromEmployeeMaster() throws Exception;



}
