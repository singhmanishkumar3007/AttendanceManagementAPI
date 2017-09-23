package com.easybusiness.attendancemanagement.services.shift;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.easybusiness.attendancemanagement.dto.ShiftDTO;

public interface ShiftService {

    public ResponseEntity<ShiftDTO> getShiftById(String shiftId);

    public ResponseEntity<ShiftDTO> getShiftByName(String shiftName);

    public ResponseEntity<ShiftDTO> persistShift(
	    ShiftDTO activityDTO);

    public ResponseEntity<List<ShiftDTO>> populateShiftList() throws Exception;

    public void destroyShiftData(String shiftId);


}
