package org.example.task.tracker.domain.mapper;

import org.example.task.tracker.domain.dto.CreateTaskRequest;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.dto.UpdateTaskCommand;
import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.valueobject.TaskDescription;
import org.example.task.tracker.domain.valueobject.TaskId;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskDataMapper {

    public Task toTask(CreateTaskRequest request) {
        return Task.Builder.builder()
                .description(new TaskDescription(request.getDescription()))
                .build();
    }


    public TaskResponse toTaskResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setUuid(task.getId().getValue());
        taskResponse.setDescription(task.getDescription().getValue());
        taskResponse.setStatus(task.getStatus().toString());
        taskResponse.setCreatedAt(task.getCreatedAt().getDate());
        taskResponse.setUpdatedAt(task.getUpdatedAt().getDate());
        return taskResponse;
    }

    public List<TaskResponse> toTaskResponse(List<Task> taskList) {
        return taskList.stream().map(this::toTaskResponse).collect(Collectors.toList());
    }


    public void copyIfNotNull(UpdateTaskCommand from, Task to) {
        Task.Builder copyBuilder = Task.Builder.builder();
        copyBuilder.id(to.getId());



    }
}
