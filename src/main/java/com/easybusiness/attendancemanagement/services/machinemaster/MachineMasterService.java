package com.easybusiness.attendancemanagement.services.machinemaster;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.easybusiness.attendancemanagement.dto.MachineMasterDTO;

public interface MachineMasterService {

    public ResponseEntity<MachineMasterDTO> getMachineMasterByCode(String machineMasterCode);

    public ResponseEntity<MachineMasterDTO> getMachineMasterByDesc(String machineMasterName);
    
    public ResponseEntity<List<MachineMasterDTO>> getMachineMasterByDepartment(String department);

    public ResponseEntity<MachineMasterDTO> persistMachineMaster(
	    MachineMasterDTO machineMasterDTO);

    public ResponseEntity<List<MachineMasterDTO>> populateMachineMasterList() throws Exception;

    public void destroyMachineMasterData(String machineMasterCode);


}
