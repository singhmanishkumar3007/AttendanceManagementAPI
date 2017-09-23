package com.easybusiness.attendancemanagement.services.employeemaster;

import static com.easybusiness.attendancemanagement.constant.AttendanceManagementConstant.USER_HOST_SERVER;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easybusiness.attendancepersistence.employeemaster.EmployeeMasterDao;
import com.easybusiness.attendancepersistence.entity.EmployeeMaster;

@RestController
@RequestMapping("/easybusiness/employeemaster/")
public class EmployeeMasterServiceImpl implements EmployeeMasterService {

    @Autowired
    private EmployeeMasterDao employeeMasterDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeMasterServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getByEbId/{ebId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EmployeeMaster> getEmployeeMasterByEbId(@PathVariable("ebId") String ebId) {
	EmployeeMaster employeeMasterEntity = null;
	try {
	    employeeMasterEntity = employeeMasterDao.findEmployeeMasterByEbId(ebId);
	} catch (Exception e) {
	    LOGGER.error("exception while getting employeeMaster by Eb Id {} , {}", ebId, e.getMessage());
	}
	return new ResponseEntity<EmployeeMaster>(employeeMasterEntity, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getByEbFixedId/{ebFixedId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EmployeeMaster> getEmployeeMasterByEbFixedId(@PathVariable("ebFixedId") String ebFixedId) {
	EmployeeMaster employeeMasterEntity = null;
	try {
	    employeeMasterEntity = employeeMasterDao.findEmployeeMasterByEbFixedId(ebFixedId);
	} catch (Exception e) {
	    LOGGER.error("exception while getting employeeMaster by eb fixed id {} , {}", ebFixedId, e.getMessage());
	}
	return new ResponseEntity<EmployeeMaster>(employeeMasterEntity, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAllFromEmployeeMaster", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EmployeeMaster>> getAllFromEmployeeMaster() throws Exception {
	List<EmployeeMaster> employeeMasterEntityList = employeeMasterDao.findAll();
	return new ResponseEntity<List<EmployeeMaster>>(employeeMasterEntityList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getEmployeeMasterByUserId/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EmployeeMaster>> getEmployeeMasterByUserId(@PathVariable("userId") String userId) {

	List<EmployeeMaster> employeeMasterEntityList = employeeMasterDao.findEmployeeMasterByUserId(userId);
	return new ResponseEntity<List<EmployeeMaster>>(employeeMasterEntityList, HttpStatus.OK);
    }

}
