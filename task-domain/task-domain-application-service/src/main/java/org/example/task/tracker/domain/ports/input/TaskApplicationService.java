package org.example.task.tracker.domain.ports.input;

import org.example.task.tracker.domain.dto.CreateTaskRequest;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.dto.UpdateTaskCommand;
import org.example.task.tracker.domain.exception.TaskDomainException;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;
import org.example.task.tracker.domain.valueobject.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskApplicationService {

     TaskResponse addTask(CreateTaskRequest createTaskRequest) throws DataAccessException, TaskDomainException;
     TaskResponse updateTask(UpdateTaskCommand updateTaskCommand);
     boolean deleteTask(UUID uuid);
     List<TaskResponse> listAllTasks();
     List<TaskResponse> listAllTasksByStatus(TaskStatus status);
     TaskResponse markTaskAs(TaskStatus status);



}
