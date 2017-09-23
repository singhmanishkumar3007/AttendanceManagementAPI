package com.easybusiness.attendancemanagement.services.usereffort;

import static com.easybusiness.attendancemanagement.constant.AttendanceManagementConstant.USER_HOST_SERVER;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easybusiness.attendancemanagement.dto.ActivityDTO;
import com.easybusiness.attendancemanagement.dto.BiometricTransactionDTO;
import com.easybusiness.attendancemanagement.dto.DepartmentDTO;
import com.easybusiness.attendancemanagement.dto.DesignationDTO;
import com.easybusiness.attendancemanagement.dto.LocationMasterDTO;
import com.easybusiness.attendancemanagement.dto.OrganizationDTO;
import com.easybusiness.attendancemanagement.dto.TaskDTO;
import com.easybusiness.attendancemanagement.dto.UserDTO;
import com.easybusiness.attendancemanagement.dto.UserDeviceEffortDTO;
import com.easybusiness.attendancemanagement.dto.UserDeviceMapDTO;
import com.easybusiness.attendancepersistence.activity.ActivityDao;
import com.easybusiness.attendancepersistence.entity.Activity;
import com.easybusiness.attendancepersistence.entity.BiometricTransaction;
import com.easybusiness.attendancepersistence.entity.Department;
import com.easybusiness.attendancepersistence.entity.Designation;
import com.easybusiness.attendancepersistence.entity.LocationMaster;
import com.easybusiness.attendancepersistence.entity.Organization;
import com.easybusiness.attendancepersistence.entity.Task;
import com.easybusiness.attendancepersistence.entity.User;
import com.easybusiness.attendancepersistence.entity.UserDeviceEffort;
import com.easybusiness.attendancepersistence.entity.UserDeviceMap;
import com.easybusiness.attendancepersistence.task.TaskDao;
import com.easybusiness.attendancepersistence.user.UserDao;
import com.easybusiness.attendancepersistence.userdeviceeffort.UserDeviceEffortDao;

@RestController
@RequestMapping("/easybusiness/effort/")
public class UserEffortServiceImpl implements UserEffortService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDeviceEffortDao userEffortDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private ActivityDao activityDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserEffortServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getEffortByEffortId/{effortId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserDeviceEffortDTO>> getEffortByEffortId(@PathVariable("effortId") String effortId) {
	List<UserDeviceEffort> userDeviceEffortEntityList = userEffortDao
		.findUserDeviceEffortById(Long.parseLong(effortId));
	List<UserDeviceEffortDTO> userDeviceEffortDTOList = new ArrayList<UserDeviceEffortDTO>();
	for (UserDeviceEffort userDeviceEffortEntity : userDeviceEffortEntityList) {
	    UserDeviceEffortDTO userDeviceEffortDTO = prepareUserDeviceEffortDTO(userDeviceEffortEntity);
	    userDeviceEffortDTOList.add(userDeviceEffortDTO);
	}
	return new ResponseEntity<List<UserDeviceEffortDTO>>(userDeviceEffortDTOList, HttpStatus.OK);
    }

    private UserDeviceEffortDTO prepareUserDeviceEffortDTO(UserDeviceEffort userDeviceEffortEntity) {
	UserDeviceEffortDTO userDeviceEffortDTO = new UserDeviceEffortDTO();
	userDeviceEffortDTO.setActivity(prepareActivityDTO(userDeviceEffortEntity.getActivity()));
	userDeviceEffortDTO.setActualEffortPerTask(userDeviceEffortEntity.getActualEffortPerTask());
	userDeviceEffortDTO.setBiometricTransaction(
		prepareBiometricTransactionDTO(userDeviceEffortEntity.getBiometricTransaction()));
	userDeviceEffortDTO.setEffortDate(userDeviceEffortEntity.getEffortDate());
	userDeviceEffortDTO.setId(userDeviceEffortEntity.getId());
	userDeviceEffortDTO.setModifiedBy(userDeviceEffortEntity.getModifiedBy());
	userDeviceEffortDTO.setModifiedOn(userDeviceEffortEntity.getModifiedOn());
	userDeviceEffortDTO.setTask(prepareTaskDTO(userDeviceEffortEntity.getTask()));
	userDeviceEffortDTO.setTimeInCampus(userDeviceEffortEntity.getTimeInCampus());
	userDeviceEffortDTO.setTimeInFloor(userDeviceEffortEntity.getTimeInFloor());
	userDeviceEffortDTO.setTimeOutsideFloor(userDeviceEffortEntity.getTimeOutsideFloor());
	userDeviceEffortDTO.setTotalEffort(userDeviceEffortEntity.getTotalEffort());
	userDeviceEffortDTO.setUser(prepareUserDTO(userDeviceEffortEntity.getUser()));
	return userDeviceEffortDTO;
    }

    private BiometricTransactionDTO prepareBiometricTransactionDTO(BiometricTransaction userDeviceTransactionEntity) {
	BiometricTransactionDTO userDeviceTransactionDTO = new BiometricTransactionDTO();
	userDeviceTransactionDTO.setTransactionId(userDeviceTransactionEntity.getTransactionId());
	userDeviceTransactionDTO.setFirstInTime(userDeviceTransactionEntity.getFirstInTime());
	userDeviceTransactionDTO.setInDate(userDeviceTransactionEntity.getInDate());
	userDeviceTransactionDTO.setLastOutTime(userDeviceTransactionEntity.getLastOutTime());
	userDeviceTransactionDTO.setTotalTimeOnFloor(userDeviceTransactionEntity.getTotalTimeOnFloor());
	userDeviceTransactionDTO.setUser(prepareUserDTO(userDeviceTransactionEntity.getUser()));
	userDeviceTransactionDTO.setUserDeviceLocation(userDeviceTransactionEntity.getUserDeviceLocation());
	userDeviceTransactionDTO
		.setUserDeviceMap(prepareUserDeviceMapDTO(userDeviceTransactionEntity.getUserDeviceMap()));
	return userDeviceTransactionDTO;
    }

    private TaskDTO prepareTaskDTO(Task taskEntity) {
	TaskDTO taskDTO = new TaskDTO();
	taskDTO.setActivity(prepareActivityDTO(taskEntity.getActivity()));
	taskDTO.setId(taskEntity.getId());
	taskDTO.setModifiedBy(taskEntity.getModifiedBy());
	taskDTO.setModifiedOn(taskEntity.getModifiedOn());
	taskDTO.setTaskName(taskEntity.getTaskName());
	return taskDTO;
    }

    private ActivityDTO prepareActivityDTO(Activity activityEntity) {
	ActivityDTO activityDTO = new ActivityDTO();
	if (null != activityEntity) {
	    activityDTO.setActivityEndDate(activityEntity.getActivityEndDate());
	    activityDTO.setActivityName(activityEntity.getActivityName());
	    activityDTO.setActivityStartDate(activityEntity.getActivityStartDate());
	    activityDTO.setId(activityEntity.getId());
	    activityDTO.setActivityStatus(activityEntity.getActivityStatus());
	    activityDTO.setModifiedBy(activityEntity.getModifiedBy());
	    activityDTO.setModifiedOn(activityEntity.getModifiedOn());
	}
	return activityDTO;
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getEffortByUserId/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserDeviceEffortDTO>> getEffortByUserId(@PathVariable("userId") String userId) {
	User user = userDao.findUserById(Long.parseLong(userId));
	List<UserDeviceEffort> userDeviceEffortEntityList = userEffortDao.findByUser(user);
	List<UserDeviceEffortDTO> userDeviceEffortDTOList = new ArrayList<UserDeviceEffortDTO>();
	for (UserDeviceEffort userDeviceEffortEntity : userDeviceEffortEntityList) {
	    UserDeviceEffortDTO userDeviceEffortDTO = prepareUserDeviceEffortDTO(userDeviceEffortEntity);
	    userDeviceEffortDTOList.add(userDeviceEffortDTO);
	}
	return new ResponseEntity<List<UserDeviceEffortDTO>>(userDeviceEffortDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getEffortByUserName/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserDeviceEffortDTO>> getEffortByUserName(@PathVariable("userName") String userName) {

	User user = userDao.findByUserName(userName);
	List<UserDeviceEffort> userDeviceEffortEntityList = userEffortDao.findByUser(user);
	List<UserDeviceEffortDTO> userDeviceEffortDTOList = new ArrayList<UserDeviceEffortDTO>();
	for (UserDeviceEffort userDeviceEffortEntity : userDeviceEffortEntityList) {
	    UserDeviceEffortDTO userDeviceEffortDTO = prepareUserDeviceEffortDTO(userDeviceEffortEntity);
	    userDeviceEffortDTOList.add(userDeviceEffortDTO);
	}
	return new ResponseEntity<List<UserDeviceEffortDTO>>(userDeviceEffortDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getEffortByUserIdAndDate/{userId}/{attendanceDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserDeviceEffortDTO>> getEffortByUserIdAndEffortDate(
	    @PathVariable("userId") String userId, @PathVariable("attendanceDate") String attendanceDate) {

	Date attenDate;
	List<UserDeviceEffortDTO> userDeviceEffortDTOList = new ArrayList<UserDeviceEffortDTO>();
	try {
	    attenDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(attendanceDate)).getTime());
	    User user = userDao.findUserById(Long.parseLong(userId));
	    List<UserDeviceEffort> userDeviceEffortEntityList = userEffortDao.findEffortForUserAndInDate(user,
		    attenDate);

	    for (UserDeviceEffort userDeviceEffortEntity : userDeviceEffortEntityList) {
		UserDeviceEffortDTO userDeviceEffortDTO = prepareUserDeviceEffortDTO(userDeviceEffortEntity);
		userDeviceEffortDTOList.add(userDeviceEffortDTO);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return new ResponseEntity<List<UserDeviceEffortDTO>>(userDeviceEffortDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getEffortByUserNameAndDate/{userName}/{attendanceDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserDeviceEffortDTO>> getEffortByUserNameAndEffortDate(
	    @PathVariable("userName") String userName, @PathVariable("attendanceDate") String attendanceDate) {

	Date attenDate;
	List<UserDeviceEffortDTO> userDeviceEffortDTOList = new ArrayList<UserDeviceEffortDTO>();
	try {
	    attenDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(attendanceDate)).getTime());
	    User user = userDao.findByUserName(userName);
	    List<UserDeviceEffort> userDeviceEffortEntityList = userEffortDao.findEffortForUserAndInDate(user,
		    attenDate);

	    for (UserDeviceEffort userDeviceEffortEntity : userDeviceEffortEntityList) {
		UserDeviceEffortDTO userDeviceEffortDTO = prepareUserDeviceEffortDTO(userDeviceEffortEntity);
		userDeviceEffortDTOList.add(userDeviceEffortDTO);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return new ResponseEntity<List<UserDeviceEffortDTO>>(userDeviceEffortDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getEffortByUserIdAndTaskIdAndDate/{userId}/{taskId}/{effortDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserDeviceEffortDTO> getEffortByUserIdAndParticularTaskAndParticularDate(
	    @PathVariable("userId") String userId, @PathVariable("taskId") String taskId,
	    @PathVariable("effortDate") String effortDate) {

	Date startAttenDate;
	UserDeviceEffort userDeviceEffortEntity = null;
	try {
	    startAttenDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(effortDate)).getTime());
	    User user = userDao.findUserById(Long.parseLong(userId));
	    Task task = taskDao.findTaskById(Long.parseLong(taskId));
	    userDeviceEffortEntity = userEffortDao.findEffortForUserAndTaskAndInDate(user, task, startAttenDate);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return new ResponseEntity<UserDeviceEffortDTO>(prepareUserDeviceEffortDTO(userDeviceEffortEntity),
		HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getEffortByUserIdAndActivityIdAndDate/{userId}/{activityId}/{effortDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserDeviceEffortDTO> getEffortByUserIdAndParticularActivityAndParticularDate(
	    @PathVariable("userId") String userId, @PathVariable("activityId") String activityId,
	    @PathVariable("effortDate") String effortDate) {

	Date startAttenDate;
	UserDeviceEffort userDeviceEffortEntity = null;
	try {
	    startAttenDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(effortDate)).getTime());
	    User user = userDao.findUserById(Long.parseLong(userId));
	    Activity activity = activityDao.findActivityById(Long.parseLong(activityId));
	    userDeviceEffortEntity = userEffortDao.findEffortForUserAndActivityAndInDate(user, activity,
		    startAttenDate);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return new ResponseEntity<UserDeviceEffortDTO>(prepareUserDeviceEffortDTO(userDeviceEffortEntity),
		HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "saveEffort", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserDeviceEffortDTO> persistUserEffort(@RequestBody UserDeviceEffortDTO userDeviceEffortDTO) {

	userEffortDao.addUserDeviceEffort(prepareUserDeviceEffortEntity(userDeviceEffortDTO));
	return new ResponseEntity<UserDeviceEffortDTO>(userDeviceEffortDTO, HttpStatus.CREATED);
    }

    private UserDeviceEffort prepareUserDeviceEffortEntity(UserDeviceEffortDTO userDeviceEffortDTO) {
	UserDeviceEffort userDeviceEffort = new UserDeviceEffort();
	userDeviceEffort.setActivity(prepareActivityEntity(userDeviceEffortDTO.getActivity()));
	userDeviceEffort.setActualEffortPerTask(userDeviceEffortDTO.getActualEffortPerTask());
	userDeviceEffort.setBiometricTransaction(
		prepareBiometricTransactionEntity(userDeviceEffortDTO.getBiometricTransaction()));
	userDeviceEffort.setEffortDate(userDeviceEffortDTO.getEffortDate());
	userDeviceEffort.setId(userDeviceEffortDTO.getId());
	userDeviceEffort.setModifiedBy(userDeviceEffortDTO.getModifiedBy());
	userDeviceEffort.setModifiedOn(userDeviceEffortDTO.getModifiedOn());
	userDeviceEffort.setTask(prepareTaskEntity(userDeviceEffortDTO.getTask()));
	userDeviceEffort.setTimeInCampus(userDeviceEffortDTO.getTimeInCampus());
	userDeviceEffort.setTimeInFloor(userDeviceEffortDTO.getTimeInFloor());
	userDeviceEffort.setTimeOutsideFloor(userDeviceEffortDTO.getTimeOutsideFloor());
	userDeviceEffort.setTotalEffort(userDeviceEffortDTO.getTotalEffort());
	userDeviceEffort.setUser(prepareUserEntity(userDeviceEffortDTO.getUser()));
	return userDeviceEffort;
    }

    private BiometricTransaction prepareBiometricTransactionEntity(BiometricTransactionDTO biometricTransaction) {

	BiometricTransaction biometricTransactionEntity = new BiometricTransaction();
	biometricTransactionEntity.setFirstInTime(biometricTransaction.getFirstInTime());
	biometricTransactionEntity.setInDate(biometricTransaction.getInDate());
	biometricTransactionEntity.setLastOutTime(biometricTransaction.getLastOutTime());
	biometricTransactionEntity.setTotalTimeOnFloor(biometricTransaction.getTotalTimeOnFloor());
	biometricTransactionEntity.setTransactionId(biometricTransaction.getTransactionId());
	biometricTransactionEntity.setUser(prepareUserEntity(biometricTransaction.getUser()));
	biometricTransactionEntity.setUserDeviceLocation(biometricTransaction.getUserDeviceLocation());
	biometricTransactionEntity
		.setUserDeviceMap(prepareUserDeviceMapEntity(biometricTransaction.getUserDeviceMap()));
	return biometricTransactionEntity;
    }

    private UserDeviceMap prepareUserDeviceMapEntity(UserDeviceMapDTO userDeviceMap) {

	UserDeviceMap userDeviceMapEntity = new UserDeviceMap();
	userDeviceMapEntity.setCreatedBy(userDeviceMap.getCreatedBy());
	userDeviceMapEntity.setCreatedOn(userDeviceMap.getCreatedOn());
	userDeviceMapEntity.setDeviceId(userDeviceMap.getDeviceId());
	userDeviceMapEntity.setId(userDeviceMap.getId());
	userDeviceMapEntity.setModifiedBy(userDeviceMap.getModifiedBy());
	userDeviceMapEntity.setModifiedOn(userDeviceMap.getModifiedOn());
	userDeviceMapEntity.setUser(prepareUserEntity(userDeviceMap.getUser()));
	return userDeviceMapEntity;
    }

    private User prepareUserEntity(UserDTO userDTO) {
	User userEntity = new User();
	userEntity.setAlternateEmail(userDTO.getAlternateEmail());
	userEntity.setDateOfBirth(userDTO.getDateOfBirth());
	Department dept = new Department();
	dept.setDeptName(userDTO.getDepartment().getDeptName());
	dept.setId(userDTO.getDepartment().getId());
	Organization org = new Organization();
	org.setId(userDTO.getDepartment().getOrganization().getId());
	org.setOrgLocation(userDTO.getDepartment().getOrganization().getOrgLocation());
	org.setOrgName(userDTO.getDepartment().getOrganization().getOrgName());
	dept.setOrganization(org);
	userEntity.setDepartment(dept);
	Designation desg = new Designation();
	desg.setDesig(userDTO.getDesignation().getDesig());
	desg.setId(userDTO.getDesignation().getId());
	userEntity.setDesignation(desg);
	userEntity.setEmail(userDTO.getEmail());
	userEntity.setEndDate(userDTO.getEndDate());
	userEntity.setFirstName(userDTO.getFirstName());
	userEntity.setFromDate(userDTO.getFromDate());
	userEntity.setGender(userDTO.getGender());
	userEntity.setIsEnabled(userDTO.getIsEnabled());
	userEntity.setLastName(userDTO.getLastName());
	userEntity.setMobile(userDTO.getMobile());
	userEntity.setModifiedBy(userDTO.getModifiedBy());
	userEntity.setModifiedOn(userDTO.getModifiedOn());
	userEntity.setOrganization(org);
	userEntity.setPassword(userDTO.getPassword());
	userEntity.setTypeOfEmployment(userDTO.getTypeOfEmployment());
	userEntity.setUserName(userDTO.getUserName());
	userEntity.setId(userDTO.getId());

	userEntity.setPermAddr(userDTO.getPermAddr());
	userEntity.setState(userDTO.getState());
	userEntity.setCity(userDTO.getCity());
	userEntity.setCountry(userDTO.getCountry());
	userEntity.setZip(userDTO.getZip());
	userEntity.setFatherName(userDTO.getFatherName());
	userEntity.setSpouseName(userDTO.getSpouseName());
	userEntity.setPassport(userDTO.getPassport());
	userEntity.setLocation(null==userDTO.getLocation()?null:prepareLocationEntity(userDTO.getLocation()));
	userEntity.setUnitId(userDTO.getUnitId());

	return userEntity;
    }
    
    private LocationMaster prepareLocationEntity(LocationMasterDTO location) {
	LocationMaster locationMaster=new LocationMaster();
	locationMaster.setCreatedBy(location.getCreatedBy());
	locationMaster.setCreatedOn(location.getCreatedOn());
	locationMaster.setId(location.getId());
	locationMaster.setLocationArea(location.getLocationArea());
	locationMaster.setLocationCity(location.getLocationCity());
	locationMaster.setLocationCountry(location.getLocationCountry());
	locationMaster.setLocationPin(location.getLocationPin());
	locationMaster.setLocationState(location.getLocationState());
	locationMaster.setModifiedBy(location.getModifiedBy());
	locationMaster.setModifiedOn(location.getModifiedOn());
	return locationMaster;
    }

    private Task prepareTaskEntity(TaskDTO taskDTO) {

	Task task = new Task();
	task.setActivity(prepareActivityEntity(taskDTO.getActivity()));
	task.setModifiedBy(taskDTO.getModifiedBy());
	task.setModifiedOn(taskDTO.getModifiedOn());
	task.setTaskName(taskDTO.getTaskName());
	return task;
    }

    private Activity prepareActivityEntity(ActivityDTO activityDTO) {

	Activity activity = new Activity();
	activity.setActivityEndDate(activityDTO.getActivityEndDate());
	activity.setActivityName(activityDTO.getActivityName());
	activity.setActivityStartDate(activityDTO.getActivityStartDate());
	activity.setActivityStatus(activityDTO.getActivityStatus());
	activity.setModifiedBy(activityDTO.getModifiedBy());
	activity.setModifiedOn(activityDTO.getModifiedOn());
	return activity;
    }

    @Override
    public void destroyEffort(String transactionId) {
	// TODO Auto-generated method stub

    }

    private UserDeviceMapDTO prepareUserDeviceMapDTO(UserDeviceMap userDeviceMapEntity) {
	UserDeviceMapDTO userDeviceMapDTO = new UserDeviceMapDTO();
	userDeviceMapDTO.setCreatedBy(userDeviceMapEntity.getCreatedBy());
	userDeviceMapDTO.setCreatedOn(userDeviceMapEntity.getCreatedOn());
	userDeviceMapDTO.setDeviceId(userDeviceMapEntity.getDeviceId());
	userDeviceMapDTO.setId(userDeviceMapEntity.getId());
	userDeviceMapDTO.setModifiedBy(userDeviceMapEntity.getModifiedBy());
	userDeviceMapDTO.setModifiedOn(userDeviceMapEntity.getModifiedOn());
	userDeviceMapDTO.setUser(prepareUserDTO(userDeviceMapEntity.getUser()));
	return userDeviceMapDTO;

    }

    private UserDTO prepareUserDTO(User userEntity) {
	UserDTO userDTO = new UserDTO();
	userDTO.setAlternateEmail(userEntity.getAlternateEmail());
	userDTO.setDateOfBirth(userEntity.getDateOfBirth());
	DepartmentDTO deptDO = new DepartmentDTO();
	try {
	    deptDO.setDeptName(userEntity.getDepartment().getDeptName());
	    deptDO.setId(userEntity.getDepartment().getId());
	    OrganizationDTO orgDTO = new OrganizationDTO();
	    orgDTO.setId(userEntity.getDepartment().getOrganization().getId());
	    orgDTO.setOrgLocation(userEntity.getDepartment().getOrganization().getOrgLocation());
	    orgDTO.setOrgName(userEntity.getDepartment().getOrganization().getOrgName());
	    deptDO.setOrganization(orgDTO);
	    userDTO.setDepartment(deptDO);
	    userDTO.setOrganization(orgDTO);
	} catch (Exception e) {
	    LOGGER.error("error in getting organization/department of user {} {}", userEntity.getUserName(),
		    e.getMessage());
	}
	try {
	    DesignationDTO desigDTO = new DesignationDTO();

	    desigDTO.setDesig(userEntity.getDesignation().getDesig());
	    desigDTO.setId(userEntity.getDesignation().getId());

	    userDTO.setDesignation(desigDTO);
	} catch (Exception e) {
	    LOGGER.error("error in getting designation of user {} {}", userEntity.getUserName(), e.getMessage());
	}
	userDTO.setEmail(userEntity.getEmail());
	userDTO.setEndDate(userEntity.getEndDate());
	userDTO.setFirstName(userEntity.getFirstName());
	userDTO.setFromDate(userEntity.getFromDate());
	userDTO.setGender(userEntity.getGender());
	userDTO.setId(userEntity.getId());
	userDTO.setIsEnabled(userEntity.getIsEnabled());
	userDTO.setLastName(userEntity.getLastName());
	userDTO.setMobile(userEntity.getMobile());
	userDTO.setModifiedBy(userEntity.getModifiedBy());
	userDTO.setModifiedOn(userEntity.getModifiedOn());

	userDTO.setPassword(userEntity.getPassword());
	userDTO.setTypeOfEmployment(userEntity.getTypeOfEmployment());
	userDTO.setUserImg(null);

	userDTO.setUserName(userEntity.getUserName());
	userDTO.setPermAddr(userEntity.getPermAddr());
	userDTO.setState(userEntity.getState());
	userDTO.setCity(userEntity.getCity());
	userDTO.setCountry(userEntity.getCountry());
	userDTO.setZip(userEntity.getZip());
	userDTO.setFatherName(userEntity.getFatherName());
	userDTO.setSpouseName(userEntity.getSpouseName());
	userDTO.setPassport(userEntity.getPassport());
	userDTO.setLocation(null!=userEntity.getLocation()?prepareLocationDTO(userEntity.getLocation()):null);
	userDTO.setUnitId(userEntity.getUnitId());

	return userDTO;
    }
    
    private LocationMasterDTO prepareLocationDTO(LocationMaster location) {
  	LocationMasterDTO locationMaster=new LocationMasterDTO();
  	locationMaster.setCreatedBy(location.getCreatedBy());
  	locationMaster.setCreatedOn(location.getCreatedOn());
  	locationMaster.setId(location.getId());
  	locationMaster.setLocationArea(location.getLocationArea());
  	locationMaster.setLocationCity(location.getLocationCity());
  	locationMaster.setLocationCountry(location.getLocationCountry());
  	locationMaster.setLocationPin(location.getLocationPin());
  	locationMaster.setLocationState(location.getLocationState());
  	locationMaster.setModifiedBy(location.getModifiedBy());
  	locationMaster.setModifiedOn(location.getModifiedOn());
  	return locationMaster;
      }

}
