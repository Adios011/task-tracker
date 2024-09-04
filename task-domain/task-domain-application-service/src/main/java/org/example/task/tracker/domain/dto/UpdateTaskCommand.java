package org.example.task.tracker.domain.dto;

import java.util.UUID;

public class UpdateTaskCommand {

    private UUID uuid;
    private String description;
    private String status ;


    public UpdateTaskCommand(UUID uuid, String description, String status) {
        this.uuid = uuid;
        this.description = description;
        this.status = status;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
