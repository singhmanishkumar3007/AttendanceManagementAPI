package com.easybusiness.attendancemanagement.services.task;

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
import com.easybusiness.attendancemanagement.dto.TaskDTO;
import com.easybusiness.attendancepersistence.entity.Activity;
import com.easybusiness.attendancepersistence.entity.Task;
import com.easybusiness.attendancepersistence.task.TaskDao;

@RestController
@RequestMapping("/easybusiness/task/")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getTaskById/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("taskId") String taskId) {
	Task taskEntity = null;
	try {
	    taskEntity = taskDao.findTaskById(Long.parseLong(taskId));
	} catch (Exception e) {
	    LOGGER.error("exception while getting task by Id {} , {}", taskId, e.getMessage());
	}
	return new ResponseEntity<TaskDTO>(prepareTaskDTO(taskEntity), HttpStatus.OK);
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
    @RequestMapping(value = "getTaskByName/{taskName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<TaskDTO> getTaskByName(@PathVariable("taskName") String taskName) {
	Task taskEntity = null;
	try {
	    taskEntity = taskDao.findTaskByTaskName(taskName);
	} catch (Exception e) {
	    LOGGER.error("exception while getting task by Name {} , {}", taskName, e.getMessage());
	}
	return new ResponseEntity<TaskDTO>(prepareTaskDTO(taskEntity), HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "addTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TaskDTO> persistTask(@RequestBody TaskDTO taskDTO) {

	Task task = prepareTaskEntity(taskDTO);
	taskDao.addTask(task);
	return new ResponseEntity<TaskDTO>(taskDTO, HttpStatus.CREATED);
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
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getAllTasks", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<TaskDTO>> populateTaskList() throws Exception {
	List<Task> taskEntityList = taskDao.findAll();
	List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
	taskEntityList.forEach(taskEntity -> {
	    TaskDTO taskDTO = prepareTaskDTO(taskEntity);
	    taskDTOList.add(taskDTO);
	});
	return new ResponseEntity<List<TaskDTO>>(taskDTOList, HttpStatus.OK);
    }

    @Override
    public void destroyTaskData(String taskId) {
	// TODO Auto-generated method stub

    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getTaskByActivityId/{activityId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<TaskDTO>> getTaskByActivityId(@PathVariable("activityId") String activityId) {

	List<Task> taskEntityList = taskDao.findTaskByActivityId(Long.parseLong(activityId));
	List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
	taskEntityList.forEach(taskEntity -> {
	    TaskDTO taskDTO = prepareTaskDTO(taskEntity);
	    taskDTOList.add(taskDTO);
	});
	return new ResponseEntity<List<TaskDTO>>(taskDTOList, HttpStatus.OK);
    }

    @Override
    @CrossOrigin(origins = USER_HOST_SERVER)
    @RequestMapping(value = "getTaskByActivityName/{activityName}", method = RequestMethod.GET)

    @ResponseBody
    public ResponseEntity<List<TaskDTO>> getTaskByActivityName(@PathVariable("activityName") String activityName) {
	List<Task> taskEntityList = taskDao.findTaskByActivityName(activityName);
	List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
	taskEntityList.forEach(taskEntity -> {
	    TaskDTO taskDTO = prepareTaskDTO(taskEntity);
	    taskDTOList.add(taskDTO);
	});
	return new ResponseEntity<List<TaskDTO>>(taskDTOList, HttpStatus.OK);
    }

}
