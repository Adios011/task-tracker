package org.example.task.tracker.domain.entity;

import org.example.task.tracker.domain.exception.TaskDomainException;
import org.example.task.tracker.domain.exception.TaskErrorMessages;
import org.example.task.tracker.domain.exception.TaskInputRules;
import org.example.task.tracker.domain.valueobject.*;

import java.util.*;

public class Task extends BaseEntity<TaskId> {

    private TaskDescription description;
    private TaskStatus status;
    private CreatedAt createdAt;
    private UpdatedAt updatedAt;


    public void init() throws TaskDomainException {
        validate();
        setId(new TaskId(UUID.randomUUID()));
        status = TaskStatus.TODO;
        createdAt = new CreatedAt(new Date());
        updatedAt = new UpdatedAt(createdAt.getDate());
    }

    public void validate() throws TaskDomainException {
        validateInitialStatus();
        validateInitialProperties();
    }

    private void validateInitialStatus() throws TaskDomainException {
        if (getId() != null || status != null)
            throw new TaskDomainException(TaskErrorMessages.INVALID_STATUS_FOR_INITIALIZATION);
    }

    private void validateInitialProperties() throws TaskDomainException {
        List<String> errorMessages = new ArrayList<>();

        if (!description.valid())
            errorMessages.add(TaskInputRules.INVALID_DESCRIPTION);

        if (!errorMessages.isEmpty()) {
            StringJoiner joiner = new StringJoiner("--");
            errorMessages.forEach(joiner::add);
            throw new TaskDomainException(joiner.toString());
        }
    }

    public TaskDescription getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public UpdatedAt getUpdatedAt() {
        return updatedAt;
    }

    private Task(Builder builder) {
        setId(builder.id);
        description = builder.description;
        status = builder.status;
        createdAt = builder.createdAt;
        updatedAt = builder.updatedAt;
    }

    public void update(String newDescription, String newStatus) {
        if (newDescription != null)
            this.description = new TaskDescription(newDescription);
        if (newStatus != null)
            this.status = TaskStatus.fromString(newStatus);

        this.updatedAt = new UpdatedAt(new Date());
    }


    public static final class Builder {
        private TaskId id;
        private TaskDescription description;
        private TaskStatus status;
        private CreatedAt createdAt;
        private UpdatedAt updatedAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(TaskId val) {
            id = val;
            return this;
        }

        public Builder description(TaskDescription val) {
            description = val;
            return this;
        }

        public Builder status(TaskStatus val) {
            status = val;
            return this;
        }

        public Builder createdAt(CreatedAt val) {
            createdAt = val;
            return this;
        }

        public Builder updatedAt(UpdatedAt val) {
            updatedAt = val;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }


    @Override
    public String toString() {
        return "Task{" +
                "description=" + description.getValue();

    }
}
