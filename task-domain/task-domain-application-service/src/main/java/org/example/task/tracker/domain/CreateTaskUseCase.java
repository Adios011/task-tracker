package org.example.task.tracker.domain;

import org.example.task.tracker.domain.dto.CreateTaskRequest;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.exception.TaskDomainException;
import org.example.task.tracker.domain.mapper.TaskDataMapper;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;
import org.example.task.tracker.domain.ports.output.repository.TaskRepository;
import org.example.task.tracker.domain.service.TaskDomainService;

public class CreateTaskUseCase {

    private final TaskDataMapper taskDataMapper;
    private final TaskDomainService taskDomainService;
    private final TaskRepository taskRepository;

    public CreateTaskUseCase(TaskDataMapper taskDataMapper,
                             TaskDomainService taskDomainService,
                             TaskRepository taskRepository) {
        this.taskDataMapper = taskDataMapper;
        this.taskDomainService = taskDomainService;
        this.taskRepository = taskRepository;
    }

    public TaskResponse addTask(CreateTaskRequest createTaskRequest) throws TaskDomainException, DataAccessException {
        Task task = taskDataMapper.toTask(createTaskRequest);

        taskDomainService.validateAndInitializeTask(task);

        taskRepository.save(task);
        return taskDataMapper.toTaskResponse(task);

    }
}
