package org.example.task.tracker.domain;

import org.example.task.tracker.domain.dto.CreateTaskRequest;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.dto.UpdateTaskCommand;
import org.example.task.tracker.domain.exception.TaskDomainException;
import org.example.task.tracker.domain.ports.input.TaskApplicationService;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;
import org.example.task.tracker.domain.valueobject.TaskStatus;

import java.util.List;
import java.util.UUID;

public class TaskApplicationServiceImpl implements TaskApplicationService {

    private final CreateTaskUseCase createTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final ListAllTasksUseCase listAllTasksUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final ListAllTasksByStatusUseCase listAllTasksByStatusUseCase;

    public TaskApplicationServiceImpl(CreateTaskUseCase createTaskUseCase,
                                      DeleteTaskUseCase deleteTaskUseCase,
                                      ListAllTasksUseCase listAllTasksUseCase,
                                      UpdateTaskUseCase updateTaskUseCase,
                                      ListAllTasksByStatusUseCase listAllTasksByStatusUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.listAllTasksUseCase = listAllTasksUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.listAllTasksByStatusUseCase = listAllTasksByStatusUseCase;
    }

    @Override
    public TaskResponse addTask(CreateTaskRequest createTaskRequest) throws DataAccessException, TaskDomainException {
        return createTaskUseCase.addTask(createTaskRequest);
    }

    @Override
    public TaskResponse updateTask(UpdateTaskCommand updateTaskCommand) {
        return updateTaskUseCase.updateTask(updateTaskCommand);
    }

    @Override
    public boolean deleteTask(UUID uuid) {
        return deleteTaskUseCase.deleteTask(uuid);
    }

    @Override
    public List<TaskResponse> listAllTasks() {
        return listAllTasksUseCase.listAll();
    }

    @Override
    public List<TaskResponse> listAllTasksByStatus(TaskStatus status) {
        return listAllTasksByStatusUseCase.listAllByStatus(status);
    }

    @Override
    public TaskResponse markTaskAs(TaskStatus status) {
        return null;
    }
}
