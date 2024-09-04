package org.example.task.tracker.domain.valueobject;

import java.util.UUID;

public class TaskId extends BaseId<UUID> {


    public TaskId(UUID value) {
        super(value);
    }
}
