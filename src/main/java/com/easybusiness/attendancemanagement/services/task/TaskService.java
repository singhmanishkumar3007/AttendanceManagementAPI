package com.easybusiness.attendancemanagement.services.task;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.easybusiness.attendancemanagement.dto.TaskDTO;

public interface TaskService {

    public ResponseEntity<TaskDTO> getTaskById(String taskId);

    public ResponseEntity<TaskDTO> getTaskByName(String taskName);
    
    public ResponseEntity<List<TaskDTO>> getTaskByActivityId(String activityId);
    
    public ResponseEntity<List<TaskDTO>> getTaskByActivityName(String activityName);

    public ResponseEntity<TaskDTO> persistTask(
	    TaskDTO taskDTO);

    public ResponseEntity<List<TaskDTO>> populateTaskList() throws Exception;

    public void destroyTaskData(String taskId);


}
