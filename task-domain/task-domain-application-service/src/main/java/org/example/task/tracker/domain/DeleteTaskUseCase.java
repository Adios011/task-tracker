package org.example.task.tracker.domain;

import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.mapper.TaskDataMapper;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;
import org.example.task.tracker.domain.ports.output.repository.TaskRepository;
import org.example.task.tracker.domain.service.TaskDomainService;
import org.example.task.tracker.domain.valueobject.TaskId;

import javax.xml.crypto.Data;
import java.util.UUID;

public class DeleteTaskUseCase {

    private final TaskDataMapper taskDataMapper;
    private final TaskRepository taskRepository;
    private final TaskDomainService taskDomainService;

    public DeleteTaskUseCase(TaskDataMapper taskDataMapper, TaskRepository taskRepository, TaskDomainService taskDomainService) {
        this.taskDataMapper = taskDataMapper;
        this.taskRepository = taskRepository;
        this.taskDomainService = taskDomainService;
    }

    public boolean deleteTask(UUID uuid) throws DataAccessException {
        taskRepository.delete(new TaskId(uuid));

        return true;

    }
}
