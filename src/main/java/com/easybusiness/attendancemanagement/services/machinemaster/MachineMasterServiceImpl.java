package com.easybusiness.attendancemanagement.services.machinemaster;

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

import com.easybusiness.attendancemanagement.dto.MachineMasterDTO;
import com.easybusiness.attendancepersistence.entity.MachineMaster;
import com.easybusiness.attendancepersistence.machinemaster.MachineMasterDao;

@RestController
@RequestMapping("/easybusiness/machine/")
public class MachineMasterServiceImpl implements MachineMasterService {

    @Autowired
    private MachineMasterDao machineMasterDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(MachineMasterServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getMachineByMachineCode/{machineCode}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MachineMasterDTO> getMachineMasterByCode(@PathVariable("machineCode") String machineCode) {
	MachineMaster machineMasterEntity = null;
	try {
	    machineMasterEntity = machineMasterDao.findMachineMasterByMachineCode(machineCode);
	} catch (Exception e) {
	    LOGGER.error("exception while getting machineMaster by code {} , {}", machineCode, e.getMessage());
	}
	return new ResponseEntity<MachineMasterDTO>(prepareMachineMasterDTO(machineMasterEntity), HttpStatus.OK);
    }

    private MachineMasterDTO prepareMachineMasterDTO(MachineMaster machineMasterEntity) {
	MachineMasterDTO machineMasterDTO = new MachineMasterDTO();
	if (null != machineMasterEntity) {
	    machineMasterDTO.setActiveFlag(machineMasterEntity.getActiveFlag());
	    machineMasterDTO.setDate1(machineMasterEntity.getDate1());
	    machineMasterDTO.setDate2(machineMasterEntity.getDate2());
	    machineMasterDTO.setDate3(machineMasterEntity.getDate3());
	    machineMasterDTO.setDate4(machineMasterEntity.getDate4());
	    machineMasterDTO.setDept(machineMasterEntity.getDept());
	    machineMasterDTO.setMachineCode(machineMasterEntity.getMachineCode());
	    machineMasterDTO.setMachineDesc(machineMasterEntity.getMachineDesc());
	    machineMasterDTO.setMillFact(machineMasterEntity.getMillFact());
	    machineMasterDTO.setUser1(machineMasterEntity.getUser1());
	    machineMasterDTO.setUser2(machineMasterEntity.getUser2());
	    machineMasterDTO.setUser3(machineMasterEntity.getUser3());
	    machineMasterDTO.setUser4(machineMasterEntity.getUser4());
	}
	return machineMasterDTO;
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getMachineByMachineDesc/{machineDesc}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MachineMasterDTO> getMachineMasterByDesc(@PathVariable("machineDesc") String machineDesc) {
	MachineMaster machineMasterEntity = null;
	try {
	    machineMasterEntity = machineMasterDao.findMachineMasterByMachineDesc(machineDesc);
	} catch (Exception e) {
	    LOGGER.error("exception while getting machineMaster by desc {} , {}", machineDesc, e.getMessage());
	}
	return new ResponseEntity<MachineMasterDTO>(prepareMachineMasterDTO(machineMasterEntity), HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "addMachineMaster", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MachineMasterDTO> persistMachineMaster(@RequestBody MachineMasterDTO machineMasterDTO) {

	MachineMaster machineMaster = prepareMachineMasterEntity(machineMasterDTO);
	machineMasterDao.addMachineMaster(machineMaster);
	return new ResponseEntity<MachineMasterDTO>(machineMasterDTO, HttpStatus.CREATED);
    }

    private MachineMaster prepareMachineMasterEntity(MachineMasterDTO machineMasterDTO) {

	MachineMaster machineMaster = new MachineMaster();
	machineMaster.setActiveFlag(machineMasterDTO.getActiveFlag());
	machineMaster.setDate1(machineMasterDTO.getDate1());
	machineMaster.setDate2(machineMasterDTO.getDate2());
	machineMaster.setDate3(machineMasterDTO.getDate3());
	machineMaster.setDate4(machineMasterDTO.getDate4());
	machineMaster.setDept(machineMasterDTO.getDept());
	machineMaster.setMachineCode(machineMasterDTO.getMachineCode());
	machineMaster.setMillFact(machineMasterDTO.getMillFact());
	machineMaster.setUser1(machineMasterDTO.getUser1());
	machineMaster.setUser2(machineMasterDTO.getUser2());
	machineMaster.setUser3(machineMasterDTO.getUser3());
	machineMaster.setUser4(machineMasterDTO.getUser4());
	if (null != machineMasterDTO.getMachineCode()) {
	    machineMaster.setMachineCode(machineMasterDTO.getMachineCode());
	}
	return machineMaster;
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAllMachineMasters", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<MachineMasterDTO>> populateMachineMasterList() throws Exception {
	List<MachineMaster> machineMasterEntityList = machineMasterDao.findAll();
	List<MachineMasterDTO> machineMasterDTOList = new ArrayList<MachineMasterDTO>();
	machineMasterEntityList.forEach(machineMasterEntity -> {
	    MachineMasterDTO machineMasterDTO = prepareMachineMasterDTO(machineMasterEntity);
	    machineMasterDTOList.add(machineMasterDTO);
	});
	return new ResponseEntity<List<MachineMasterDTO>>(machineMasterDTOList, HttpStatus.OK);
    }

    @Override
    public void destroyMachineMasterData(String machineMasterId) {
	// TODO Auto-generated method stub

    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getMachinesByDepartment/{department}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<MachineMasterDTO>> getMachineMasterByDepartment(
	    @PathVariable("department") String department) {

	List<MachineMaster> machineMasterEntityList = machineMasterDao.findMachineMasterByDepartment(department);
	List<MachineMasterDTO> machineMasterDTOList = new ArrayList<MachineMasterDTO>();
	machineMasterEntityList.forEach(machineMasterEntity -> {
	    MachineMasterDTO machineMasterDTO = prepareMachineMasterDTO(machineMasterEntity);
	    machineMasterDTOList.add(machineMasterDTO);
	});
	return new ResponseEntity<List<MachineMasterDTO>>(machineMasterDTOList, HttpStatus.OK);
    }

}
