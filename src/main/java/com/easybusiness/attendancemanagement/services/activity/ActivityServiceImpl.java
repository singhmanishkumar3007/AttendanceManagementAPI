package com.easybusiness.attendancemanagement.services.activity;

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

import com.easybusiness.attendancemanagement.dto.ActivityDTO;
import com.easybusiness.attendancepersistence.activity.ActivityDao;
import com.easybusiness.attendancepersistence.entity.Activity;

@RestController
@RequestMapping("/easybusiness/activity/")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getActivityById/{activityId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("activityId") String activityId) {
	Activity activityEntity = null;
	try {
	    activityEntity = activityDao.findActivityById(Long.parseLong(activityId));
	} catch (Exception e) {
	    LOGGER.error("exception while getting activity by Id {} , {}", activityId, e.getMessage());
	}
	return new ResponseEntity<ActivityDTO>(prepareActivityDTO(activityEntity), HttpStatus.OK);
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
    @RequestMapping(value = "getActivityByName/{activityName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ActivityDTO> getActivityByName(@PathVariable("activityName") String activityName) {
	Activity activityEntity = null;
	try {
	    activityEntity = activityDao.findActivityByActivityName(activityName);
	} catch (Exception e) {
	    LOGGER.error("exception while getting activity by Name {} , {}", activityName, e.getMessage());
	}
	return new ResponseEntity<ActivityDTO>(prepareActivityDTO(activityEntity), HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "addActivity", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ActivityDTO> persistActivity(@RequestBody ActivityDTO activityDTO) {

	Activity activity = prepareActivityEntity(activityDTO);
	activityDao.addActivity(activity);
	Activity activityEntity = null;
	try {
	    activityEntity = activityDao.findActivityByActivityName(activityDTO.getActivityName());
	    activityDTO.setId(activityEntity.getId());
	} catch (Exception e) {
	    LOGGER.error("exception while getting  new created activity by Name {} , {}", activityDTO.getActivityName(),
		    e.getMessage());
	}
	return new ResponseEntity<ActivityDTO>(activityDTO, HttpStatus.CREATED);
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
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAllActivities", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ActivityDTO>> populateActivityList() throws Exception {
	List<Activity> activityEntityList = activityDao.findAll();
	List<ActivityDTO> activityDTOList = new ArrayList<ActivityDTO>();
	activityEntityList.forEach(activityEntity -> {
	    ActivityDTO activityDTO = prepareActivityDTO(activityEntity);
	    activityDTOList.add(activityDTO);
	});
	return new ResponseEntity<List<ActivityDTO>>(activityDTOList, HttpStatus.OK);
    }

    @Override
    public void destroyActivityData(String activityId) {
	// TODO Auto-generated method stub

    }

}
