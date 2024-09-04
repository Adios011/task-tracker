package org.example.task.tracker.domain;

import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.mapper.TaskDataMapper;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;
import org.example.task.tracker.domain.ports.output.repository.TaskRepository;
import org.example.task.tracker.domain.valueobject.TaskStatus;

import java.util.List;

public class ListAllTasksByStatusUseCase {

    private final TaskRepository taskRepository;
    private final TaskDataMapper taskDataMapper;

    public ListAllTasksByStatusUseCase(TaskRepository taskRepository, TaskDataMapper taskDataMapper) {
        this.taskRepository = taskRepository;
        this.taskDataMapper = taskDataMapper;
    }


    public List<TaskResponse> listAllByStatus(TaskStatus taskStatus) throws DataAccessException {
        List<Task> tasks = taskRepository.findAllByStatus(taskStatus);
        return taskDataMapper.toTaskResponse(tasks);
    }

}
