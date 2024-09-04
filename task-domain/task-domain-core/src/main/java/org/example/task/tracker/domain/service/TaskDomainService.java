package org.example.task.tracker.domain.service;

import org.example.task.tracker.domain.entity.Task;

public interface TaskDomainService {

     Task validateAndInitializeTask(Task task);

     Task update(Task task , String newDescription, String newStatus);
}
