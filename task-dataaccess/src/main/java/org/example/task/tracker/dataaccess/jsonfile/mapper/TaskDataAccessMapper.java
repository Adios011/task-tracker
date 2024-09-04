package org.example.task.tracker.dataaccess.jsonfile.mapper;

import org.example.task.tracker.dataaccess.jsonfile.entity.TaskJsonEntity;
import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.valueobject.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskDataAccessMapper {

    public TaskJsonEntity toJsonEntity(Task task) {
        TaskJsonEntity jsonEntity = new TaskJsonEntity();
        jsonEntity.setId(task.getId().getValue());
        jsonEntity.setDescription(task.getDescription().getValue());
        jsonEntity.setStatus(task.getStatus().toString());
        jsonEntity.setCreatedAt(task.getCreatedAt().getDate());
        jsonEntity.setUpdatedAt(task.getUpdatedAt().getDate());


        return jsonEntity;

    }

    public Task toDomainEntity(TaskJsonEntity from) {
        return Task.Builder.builder()
                .id(new TaskId(from.getId()))
                .description(new TaskDescription(from.getDescription()))
                .status(TaskStatus.fromString(from.getStatus()))
                .createdAt(new CreatedAt(from.getCreatedAt()))
                .updatedAt(new UpdatedAt(from.getUpdatedAt()))
                .build();
    }

    public List<Task> toDomainEntity(List<TaskJsonEntity> from){
        return from.stream().map(this::toDomainEntity)
                .collect(Collectors.toList());

    }

    public void copy(TaskJsonEntity from, TaskJsonEntity to) {
        to.setId(from.getId());
        to.setDescription(from.getDescription());
        to.setStatus(from.getStatus());
        to.setUpdatedAt(from.getUpdatedAt());
        to.setCreatedAt(from.getCreatedAt());
    }
}
