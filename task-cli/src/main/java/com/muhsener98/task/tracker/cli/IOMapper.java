package com.muhsener98.task.tracker.cli;

import org.example.task.tracker.domain.dto.CreateTaskRequest;
import org.example.task.tracker.domain.dto.UpdateTaskCommand;

public class IOMapper {


    public CreateTaskRequest createTaskRequest(String description){
        return new CreateTaskRequest(description);
    }


}
