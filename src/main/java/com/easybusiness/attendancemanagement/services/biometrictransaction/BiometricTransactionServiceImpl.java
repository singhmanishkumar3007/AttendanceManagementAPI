package com.easybusiness.attendancemanagement.services.biometrictransaction;

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

import com.easybusiness.attendancemanagement.dto.BiometricTransactionDTO;
import com.easybusiness.attendancemanagement.dto.DepartmentDTO;
import com.easybusiness.attendancemanagement.dto.DesignationDTO;
import com.easybusiness.attendancemanagement.dto.LocationMasterDTO;
import com.easybusiness.attendancemanagement.dto.OrganizationDTO;
import com.easybusiness.attendancemanagement.dto.UserDTO;
import com.easybusiness.attendancemanagement.dto.UserDeviceMapDTO;
import com.easybusiness.attendancepersistence.biometricytransaction.BiometricTransactionDao;
import com.easybusiness.attendancepersistence.entity.BiometricTransaction;
import com.easybusiness.attendancepersistence.entity.Department;
import com.easybusiness.attendancepersistence.entity.Designation;
import com.easybusiness.attendancepersistence.entity.LocationMaster;
import com.easybusiness.attendancepersistence.entity.Organization;
import com.easybusiness.attendancepersistence.entity.User;
import com.easybusiness.attendancepersistence.entity.UserDeviceMap;
import com.easybusiness.attendancepersistence.user.UserDao;
import com.easybusiness.attendancepersistence.userdevicemap.UserDeviceMapDao;

@RestController
@RequestMapping("/easybusiness/biometric/")
public class BiometricTransactionServiceImpl implements BiometricTransactionService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDeviceMapDao userDeviceMapDao;

    @Autowired
    private BiometricTransactionDao biometricTransactionDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(BiometricTransactionServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAttendanceByTransactionId/{tranId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BiometricTransactionDTO>> getAttendanceByTransactionId(
	    @PathVariable("tranId") String transactionId) {
	List<BiometricTransaction> biometricTransactionEntityList = biometricTransactionDao
		.findBiometricTransactionById(Long.parseLong(transactionId));
	List<BiometricTransactionDTO> biometricTransactionDTOList = new ArrayList<BiometricTransactionDTO>();
	for (BiometricTransaction biometricTransactionEntity : biometricTransactionEntityList) {
	    BiometricTransactionDTO biometricTransactionDTO = new BiometricTransactionDTO();
	    biometricTransactionDTO.setTransactionId(biometricTransactionEntity.getTransactionId());
	    biometricTransactionDTO.setFirstInTime(biometricTransactionEntity.getFirstInTime());
	    biometricTransactionDTO.setInDate(biometricTransactionEntity.getInDate());
	    biometricTransactionDTO.setLastOutTime(biometricTransactionEntity.getLastOutTime());
	    biometricTransactionDTO.setTotalTimeOnFloor(biometricTransactionEntity.getTotalTimeOnFloor());
	    biometricTransactionDTO.setUser(prepareUserDTO(biometricTransactionEntity.getUser()));
	    biometricTransactionDTO.setUserDeviceLocation(biometricTransactionEntity.getUserDeviceLocation());
	    biometricTransactionDTO
		    .setUserDeviceMap(prepareUserDeviceMapDTO(biometricTransactionEntity.getUserDeviceMap()));
	    biometricTransactionDTOList.add(biometricTransactionDTO);
	}
	return new ResponseEntity<List<BiometricTransactionDTO>>(biometricTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAttendanceByUserId/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BiometricTransactionDTO>> getAttendanceByUserId(@PathVariable("userId") String userId) {
	User user = userDao.findUserById(Long.parseLong(userId));
	List<BiometricTransaction> biometricTransactionEntityList = biometricTransactionDao.findByUser(user);
	List<BiometricTransactionDTO> biometricTransactionDTOList = new ArrayList<BiometricTransactionDTO>();
	for (BiometricTransaction biometricTransactionEntity : biometricTransactionEntityList) {
	    BiometricTransactionDTO biometricTransactionDTO = new BiometricTransactionDTO();
	    biometricTransactionDTO.setTransactionId(biometricTransactionEntity.getTransactionId());
	    biometricTransactionDTO.setFirstInTime(biometricTransactionEntity.getFirstInTime());
	    biometricTransactionDTO.setInDate(biometricTransactionEntity.getInDate());
	    biometricTransactionDTO.setLastOutTime(biometricTransactionEntity.getLastOutTime());
	    biometricTransactionDTO.setTotalTimeOnFloor(biometricTransactionEntity.getTotalTimeOnFloor());
	    biometricTransactionDTO.setUser(prepareUserDTO(biometricTransactionEntity.getUser()));
	    biometricTransactionDTO.setUserDeviceLocation(biometricTransactionEntity.getUserDeviceLocation());
	    biometricTransactionDTO
		    .setUserDeviceMap(prepareUserDeviceMapDTO(biometricTransactionEntity.getUserDeviceMap()));
	    biometricTransactionDTOList.add(biometricTransactionDTO);
	}
	return new ResponseEntity<List<BiometricTransactionDTO>>(biometricTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAttendanceByUserName/{userName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BiometricTransactionDTO>> getAttendanceByUserName(
	    @PathVariable("userName") String userName) {

	User user = userDao.findByUserName(userName);
	List<BiometricTransaction> biometricTransactionEntityList = biometricTransactionDao.findByUser(user);
	List<BiometricTransactionDTO> biometricTransactionDTOList = new ArrayList<BiometricTransactionDTO>();
	for (BiometricTransaction biometricTransactionEntity : biometricTransactionEntityList) {
	    BiometricTransactionDTO biometricTransactionDTO = new BiometricTransactionDTO();
	    biometricTransactionDTO.setTransactionId(biometricTransactionEntity.getTransactionId());
	    biometricTransactionDTO.setFirstInTime(biometricTransactionEntity.getFirstInTime());
	    biometricTransactionDTO.setInDate(biometricTransactionEntity.getInDate());
	    biometricTransactionDTO.setLastOutTime(biometricTransactionEntity.getLastOutTime());
	    biometricTransactionDTO.setTotalTimeOnFloor(biometricTransactionEntity.getTotalTimeOnFloor());
	    biometricTransactionDTO.setUser(prepareUserDTO(biometricTransactionEntity.getUser()));
	    biometricTransactionDTO.setUserDeviceLocation(biometricTransactionEntity.getUserDeviceLocation());
	    biometricTransactionDTO
		    .setUserDeviceMap(prepareUserDeviceMapDTO(biometricTransactionEntity.getUserDeviceMap()));
	    biometricTransactionDTOList.add(biometricTransactionDTO);
	}
	return new ResponseEntity<List<BiometricTransactionDTO>>(biometricTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAttendanceByUserDeviceId/{userDeviceId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BiometricTransactionDTO>> getAttendanceByUserDeviceId(
	    @PathVariable("userDeviceId") String userDeviceId) {

	UserDeviceMap userDeviceMap = userDeviceMapDao.findUserDeviceMapById(Long.parseLong(userDeviceId));
	List<BiometricTransaction> biometricTransactionEntityList = biometricTransactionDao
		.findBiometricTransactionByUserDevice(userDeviceMap);
	List<BiometricTransactionDTO> biometricTransactionDTOList = new ArrayList<BiometricTransactionDTO>();
	for (BiometricTransaction biometricTransactionEntity : biometricTransactionEntityList) {
	    BiometricTransactionDTO biometricTransactionDTO = new BiometricTransactionDTO();
	    biometricTransactionDTO.setTransactionId(biometricTransactionEntity.getTransactionId());
	    biometricTransactionDTO.setFirstInTime(biometricTransactionEntity.getFirstInTime());
	    biometricTransactionDTO.setInDate(biometricTransactionEntity.getInDate());
	    biometricTransactionDTO.setLastOutTime(biometricTransactionEntity.getLastOutTime());
	    biometricTransactionDTO.setTotalTimeOnFloor(biometricTransactionEntity.getTotalTimeOnFloor());
	    biometricTransactionDTO.setUser(prepareUserDTO(biometricTransactionEntity.getUser()));
	    biometricTransactionDTO.setUserDeviceLocation(biometricTransactionEntity.getUserDeviceLocation());
	    biometricTransactionDTO
		    .setUserDeviceMap(prepareUserDeviceMapDTO(biometricTransactionEntity.getUserDeviceMap()));
	    biometricTransactionDTOList.add(biometricTransactionDTO);
	}

	return new ResponseEntity<List<BiometricTransactionDTO>>(biometricTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAttendanceByDate/{attendanceDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BiometricTransactionDTO>> getAttendanceByAttendanceDate(
	    @PathVariable("attendanceDate") String attendanceDate) {

	Date attenDate;
	List<BiometricTransactionDTO> biometricTransactionDTOList = new ArrayList<BiometricTransactionDTO>();
	try {
	    attenDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(attendanceDate)).getTime());

	    List<BiometricTransaction> biometricTransactionEntityList = biometricTransactionDao
		    .findBiometricTransactionByParticularDate(attenDate);

	    for (BiometricTransaction biometricTransactionEntity : biometricTransactionEntityList) {
		BiometricTransactionDTO biometricTransactionDTO = new BiometricTransactionDTO();
		biometricTransactionDTO.setTransactionId(biometricTransactionEntity.getTransactionId());
		biometricTransactionDTO.setFirstInTime(biometricTransactionEntity.getFirstInTime());
		biometricTransactionDTO.setInDate(biometricTransactionEntity.getInDate());
		biometricTransactionDTO.setLastOutTime(biometricTransactionEntity.getLastOutTime());
		biometricTransactionDTO.setTotalTimeOnFloor(biometricTransactionEntity.getTotalTimeOnFloor());
		biometricTransactionDTO.setUser(prepareUserDTO(biometricTransactionEntity.getUser()));
		biometricTransactionDTO.setUserDeviceLocation(biometricTransactionEntity.getUserDeviceLocation());
		biometricTransactionDTO
			.setUserDeviceMap(prepareUserDeviceMapDTO(biometricTransactionEntity.getUserDeviceMap()));
		biometricTransactionDTOList.add(biometricTransactionDTO);
	    }

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return new ResponseEntity<List<BiometricTransactionDTO>>(biometricTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAttendanceByUserIdAndDate/{userId}/{attendanceDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BiometricTransactionDTO> getAttendanceByUserIdAndAttendanceDate(
	    @PathVariable("userId") String userId, @PathVariable("attendanceDate") String attendanceDate) {

	Date attenDate;
	BiometricTransactionDTO biometricTransactionDTO = new BiometricTransactionDTO();
	try {
	    attenDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(attendanceDate)).getTime());
	    User user = userDao.findUserById(Long.parseLong(userId));
	    BiometricTransaction biometricTransactionEntity = biometricTransactionDao
		    .findBiometricTransactionByUserAndInDate(user, attenDate);

	    biometricTransactionDTO.setTransactionId(biometricTransactionEntity.getTransactionId());
	    biometricTransactionDTO.setFirstInTime(biometricTransactionEntity.getFirstInTime());
	    biometricTransactionDTO.setInDate(biometricTransactionEntity.getInDate());
	    biometricTransactionDTO.setLastOutTime(biometricTransactionEntity.getLastOutTime());
	    biometricTransactionDTO.setTotalTimeOnFloor(biometricTransactionEntity.getTotalTimeOnFloor());
	    biometricTransactionDTO.setUser(prepareUserDTO(biometricTransactionEntity.getUser()));
	    biometricTransactionDTO.setUserDeviceLocation(biometricTransactionEntity.getUserDeviceLocation());
	    biometricTransactionDTO
		    .setUserDeviceMap(prepareUserDeviceMapDTO(biometricTransactionEntity.getUserDeviceMap()));

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return new ResponseEntity<BiometricTransactionDTO>(biometricTransactionDTO, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAttendanceByUserNameAndDate/{userName}/{attendanceDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BiometricTransactionDTO> getAttendanceByUserNameAndAttendanceDate(
	    @PathVariable("userName") String userName, @PathVariable("attendanceDate") String attendanceDate) {

	Date attenDate;
	BiometricTransactionDTO biometricTransactionDTO = new BiometricTransactionDTO();
	try {
	    attenDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(attendanceDate)).getTime());
	    User user = userDao.findByUserName(userName);
	    BiometricTransaction biometricTransactionEntity = biometricTransactionDao
		    .findBiometricTransactionByUserAndInDate(user, attenDate);

	    biometricTransactionDTO.setTransactionId(biometricTransactionEntity.getTransactionId());
	    biometricTransactionDTO.setFirstInTime(biometricTransactionEntity.getFirstInTime());
	    biometricTransactionDTO.setInDate(biometricTransactionEntity.getInDate());
	    biometricTransactionDTO.setLastOutTime(biometricTransactionEntity.getLastOutTime());
	    biometricTransactionDTO.setTotalTimeOnFloor(biometricTransactionEntity.getTotalTimeOnFloor());
	    biometricTransactionDTO.setUser(prepareUserDTO(biometricTransactionEntity.getUser()));
	    biometricTransactionDTO.setUserDeviceLocation(biometricTransactionEntity.getUserDeviceLocation());
	    biometricTransactionDTO
		    .setUserDeviceMap(prepareUserDeviceMapDTO(biometricTransactionEntity.getUserDeviceMap()));

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return new ResponseEntity<BiometricTransactionDTO>(biometricTransactionDTO, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAttendanceByUserIdAndDate/{userId}/{startAttendanceDate}/{endAttendanceDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BiometricTransactionDTO>> getAttendanceByUserIdAndAttendanceDateRange(
	    @PathVariable("userId") String userId, @PathVariable("startAttendanceDate") String startAttendanceDate,
	    @PathVariable("endAttendanceDate") String endAttendanceDate) {

	Date startAttenDate;
	Date endAttenDate;
	List<BiometricTransactionDTO> biometricTransactionDTOList = new ArrayList<BiometricTransactionDTO>();
	try {
	    startAttenDate = new java.sql.Date(
		    (new SimpleDateFormat("dd-MM-yyyy").parse(startAttendanceDate)).getTime());
	    endAttenDate = new java.sql.Date((new SimpleDateFormat("dd-MM-yyyy").parse(endAttendanceDate)).getTime());
	    User user = userDao.findUserById(Long.parseLong(userId));
	    List<BiometricTransaction> biometricTransactionEntityList = biometricTransactionDao
		    .findBiometricTransactionByUserAndDateRange(user, startAttenDate, endAttenDate);

	    for (BiometricTransaction biometricTransactionEntity : biometricTransactionEntityList) {

		BiometricTransactionDTO biometricTransactionDTO = new BiometricTransactionDTO();

		biometricTransactionDTO.setTransactionId(biometricTransactionEntity.getTransactionId());
		biometricTransactionDTO.setFirstInTime(biometricTransactionEntity.getFirstInTime());
		biometricTransactionDTO.setInDate(biometricTransactionEntity.getInDate());
		biometricTransactionDTO.setLastOutTime(biometricTransactionEntity.getLastOutTime());
		biometricTransactionDTO.setTotalTimeOnFloor(biometricTransactionEntity.getTotalTimeOnFloor());
		biometricTransactionDTO.setUser(prepareUserDTO(biometricTransactionEntity.getUser()));
		biometricTransactionDTO.setUserDeviceLocation(biometricTransactionEntity.getUserDeviceLocation());
		biometricTransactionDTO
			.setUserDeviceMap(prepareUserDeviceMapDTO(biometricTransactionEntity.getUserDeviceMap()));
		biometricTransactionDTOList.add(biometricTransactionDTO);

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return new ResponseEntity<List<BiometricTransactionDTO>>(biometricTransactionDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "saveBiometricTransaction", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BiometricTransactionDTO> persistBiometricTransaction(
	    @RequestBody BiometricTransactionDTO biometricTransactionDTO) {

	BiometricTransaction biometricTransaction = prepareBiometricTransactionEntity(biometricTransactionDTO);
	biometricTransactionDao.addBiometricTransaction(biometricTransaction);
	return new ResponseEntity<BiometricTransactionDTO>(biometricTransactionDTO, HttpStatus.CREATED);
    }

    private BiometricTransaction prepareBiometricTransactionEntity(BiometricTransactionDTO biometricTransactionDTO) {
	BiometricTransaction biometricTransaction = new BiometricTransaction();
	biometricTransaction.setTransactionId(biometricTransactionDTO.getTransactionId());
	biometricTransaction.setFirstInTime(biometricTransactionDTO.getFirstInTime());
	biometricTransaction.setInDate(biometricTransactionDTO.getInDate());
	biometricTransaction.setLastOutTime(biometricTransactionDTO.getLastOutTime());
	biometricTransaction.setTotalTimeOnFloor(biometricTransactionDTO.getTotalTimeOnFloor());
	biometricTransaction.setUser(prepareUserEntity(biometricTransactionDTO.getUser()));
	biometricTransaction.setUserDeviceLocation(biometricTransactionDTO.getUserDeviceLocation());
	biometricTransaction.setUserDeviceMap(prepareUserDeviceMapEntity(biometricTransactionDTO.getUserDeviceMap()));
	return biometricTransaction;
    }

    @Override
    public List<BiometricTransactionDTO> populateAttendanceList() throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void destroyAttendanceData(String transactionId) {
	// TODO Auto-generated method stub

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
	userEntity.setLocation(null == userDTO.getLocation() ? null : prepareLocationEntity(userDTO.getLocation()));
	userEntity.setUnitId(userDTO.getUnitId());

	return userEntity;
    }

    private LocationMaster prepareLocationEntity(LocationMasterDTO location) {
	LocationMaster locationMaster = new LocationMaster();
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

    private UserDeviceMap prepareUserDeviceMapEntity(UserDeviceMapDTO userDeviceMapDto) {
	UserDeviceMap userDeviceMap = new UserDeviceMap();
	userDeviceMap.setCreatedBy(userDeviceMapDto.getCreatedBy());
	userDeviceMap.setCreatedOn(userDeviceMapDto.getCreatedOn());
	userDeviceMap.setDeviceId(userDeviceMapDto.getDeviceId());
	userDeviceMap.setId(userDeviceMapDto.getId());
	userDeviceMap.setModifiedBy(userDeviceMapDto.getModifiedBy());
	userDeviceMap.setModifiedOn(userDeviceMapDto.getModifiedOn());
	userDeviceMap.setUser(prepareUserEntity(userDeviceMapDto.getUser()));
	return userDeviceMap;

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
	userDTO.setLocation(null != userEntity.getLocation() ? prepareLocationDTO(userEntity.getLocation()) : null);
	userDTO.setUnitId(userEntity.getUnitId());

	return userDTO;
    }

    private LocationMasterDTO prepareLocationDTO(LocationMaster location) {
	LocationMasterDTO locationMaster = new LocationMasterDTO();
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
