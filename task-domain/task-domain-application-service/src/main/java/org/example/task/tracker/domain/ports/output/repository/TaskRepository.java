package org.example.task.tracker.domain.ports.output.repository;

import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.valueobject.TaskId;
import org.example.task.tracker.domain.valueobject.TaskStatus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    void save(Task task) ;

    Optional<Task> findById(TaskId taskId);

    List<Task> findAll();

    Task update(Task task);


    boolean delete(TaskId taskId) throws DataAccessException;

    List<Task> findAllByStatus(TaskStatus taskStatus);
}
