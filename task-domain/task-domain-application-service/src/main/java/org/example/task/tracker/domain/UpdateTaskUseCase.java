package org.example.task.tracker.domain;

import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.dto.UpdateTaskCommand;
import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.exception.TaskDomainException;
import org.example.task.tracker.domain.mapper.TaskDataMapper;
import org.example.task.tracker.domain.ports.output.repository.TaskRepository;
import org.example.task.tracker.domain.service.TaskDomainService;
import org.example.task.tracker.domain.valueobject.TaskId;

import java.util.Optional;
import java.util.UUID;

public class UpdateTaskUseCase {

    private final TaskDomainService taskDomainService;
    private final TaskRepository taskRepository;
    private final TaskDataMapper taskDataMapper;

    public UpdateTaskUseCase(TaskDomainService taskDomainService, TaskRepository taskRepository, TaskDataMapper taskDataMapper) {
        this.taskDomainService = taskDomainService;
        this.taskRepository = taskRepository;
        this.taskDataMapper = taskDataMapper;
    }

    public TaskResponse updateTask(UpdateTaskCommand updateTaskCommand) throws TaskDomainException {
        Task task = fetchTask(updateTaskCommand.getUuid());
        Task updatedTask = taskDomainService.update(task, updateTaskCommand.getDescription(), updateTaskCommand.getStatus());
        taskRepository.update(updatedTask);
        return taskDataMapper.toTaskResponse(updatedTask);
    }

    private Task fetchTask(UUID uuid) {
        Optional<Task> optional = taskRepository.findById(new TaskId(uuid));
        if (optional.isEmpty())
            throw new TaskDomainException("No such tasks found: " + uuid);

        return optional.get();
    }
}
