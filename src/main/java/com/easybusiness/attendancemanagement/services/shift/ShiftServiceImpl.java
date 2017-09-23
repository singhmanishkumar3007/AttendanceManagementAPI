package com.easybusiness.attendancemanagement.services.shift;

import static com.easybusiness.attendancemanagement.constant.AttendanceManagementConstant.USER_HOST_SERVER;

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

import com.easybusiness.attendancemanagement.dto.ShiftDTO;
import com.easybusiness.attendancepersistence.entity.Shift;
import com.easybusiness.attendancepersistence.shiftmaster.ShiftDao;

@RestController
@RequestMapping("/easybusiness/shift/")
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftDao shiftDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiftServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getShiftById/{shiftId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ShiftDTO> getShiftById(@PathVariable("shiftId") String shiftId) {
	Shift shiftEntity = null;
	try {
	    shiftEntity = shiftDao.findShiftById(Long.parseLong(shiftId));
	} catch (Exception e) {
	    LOGGER.error("exception while getting shift by Id {} , {}", shiftId, e.getMessage());
	}
	return new ResponseEntity<ShiftDTO>(prepareShiftDTO(shiftEntity), HttpStatus.OK);
    }

    private ShiftDTO prepareShiftDTO(Shift shiftEntity) {
	ShiftDTO shiftDTO = new ShiftDTO();
	if (null != shiftEntity) {
	    shiftDTO.setId(shiftEntity.getId());
	    shiftDTO.setShiftName(shiftEntity.getShiftName());
	    shiftDTO.setWorkingHours(shiftEntity.getWorkingHours());
	}
	return shiftDTO;
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getShiftByName/{shiftName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ShiftDTO> getShiftByName(@PathVariable("shiftName") String shiftName) {
	Shift shiftEntity = null;
	try {
	    shiftEntity = shiftDao.findShiftByShiftName(shiftName);
	} catch (Exception e) {
	    LOGGER.error("exception while getting shift by Name {} , {}", shiftName, e.getMessage());
	}
	return new ResponseEntity<ShiftDTO>(prepareShiftDTO(shiftEntity), HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "addShift", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ShiftDTO> persistShift(@RequestBody ShiftDTO shiftDTO) {

	Shift shift = prepareShiftEntity(shiftDTO);
	shiftDao.addShift(shift);
	return new ResponseEntity<ShiftDTO>(shiftDTO, HttpStatus.CREATED);
    }

    private Shift prepareShiftEntity(ShiftDTO shiftDTO) {

	Shift shift = new Shift();
	shift.setShiftName(shiftDTO.getShiftName());
	shift.setWorkingHours(shiftDTO.getWorkingHours());
	return shift;
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAllShifts", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ShiftDTO>> populateShiftList() throws Exception {
	List<Shift> shiftEntityList = shiftDao.findAll();
	List<ShiftDTO> shiftDTOList = new ArrayList<ShiftDTO>();
	shiftEntityList.forEach(shiftEntity -> {
	    ShiftDTO shiftDTO = prepareShiftDTO(shiftEntity);
	    shiftDTOList.add(shiftDTO);
	});
	return new ResponseEntity<List<ShiftDTO>>(shiftDTOList, HttpStatus.OK);
    }

    @Override
    public void destroyShiftData(String shiftId) {
	// TODO Auto-generated method stub

    }

}
