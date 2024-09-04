package org.example.task.tracker.domain.service.impl;

import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.exception.TaskDomainException;
import org.example.task.tracker.domain.service.TaskDomainService;

public class TaskDomainServiceImpl implements TaskDomainService {

    @Override
    public Task validateAndInitializeTask(Task task) throws TaskDomainException {
        task.init();
        return task;
    }

    @Override
    public Task update(Task task, String newDescription, String newStatus) {
        task.update(newDescription , newStatus);
        return task;
    }
}
