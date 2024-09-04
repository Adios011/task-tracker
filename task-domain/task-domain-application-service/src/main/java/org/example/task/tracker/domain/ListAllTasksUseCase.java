package org.example.task.tracker.domain;

import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.mapper.TaskDataMapper;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;
import org.example.task.tracker.domain.ports.output.repository.TaskRepository;

import java.util.List;

public class ListAllTasksUseCase {

    private final TaskDataMapper taskDataMapper;
    private final TaskRepository taskRepository;

    public ListAllTasksUseCase(TaskDataMapper taskDataMapper, TaskRepository taskRepository) {
        this.taskDataMapper = taskDataMapper;
        this.taskRepository = taskRepository;
    }


    public List<TaskResponse> listAll() throws DataAccessException {
        List<Task> tasks = taskRepository.findAll();
        return taskDataMapper.toTaskResponse(tasks);
    }
}
