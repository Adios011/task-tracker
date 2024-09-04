package org.example.task.tracker.controller;

import org.example.task.tracker.domain.dto.CreateTaskRequest;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.dto.UpdateTaskCommand;
import org.example.task.tracker.domain.exception.TaskDomainException;
import org.example.task.tracker.domain.ports.input.TaskApplicationService;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;
import org.example.task.tracker.domain.valueobject.TaskStatus;

import java.util.List;
import java.util.UUID;

public class TaskCliController {

    private final TaskApplicationService taskApplicationService;



    public TaskCliController(TaskApplicationService taskApplicationService) {
        this.taskApplicationService = taskApplicationService;
    }

    public TaskResponse addTask(CreateTaskRequest request) throws TaskDomainException {
        return  taskApplicationService.addTask(request);
    }

    public TaskResponse deleteTask(UUID uuid) throws DataAccessException {
        taskApplicationService.deleteTask(uuid);
        TaskResponse response = new TaskResponse();
        response.setUuid(uuid);
        return  response;
    }

    public List<TaskResponse> listAllTasks() {
        return taskApplicationService.listAllTasks();
    }

    public TaskResponse updateTask(UpdateTaskCommand updateTaskCommand) {
        return taskApplicationService.updateTask(updateTaskCommand);
    }

    public List<TaskResponse> listAllByStatus(String arg) {
        return taskApplicationService.listAllTasksByStatus(TaskStatus.fromString(arg));
    }
}
