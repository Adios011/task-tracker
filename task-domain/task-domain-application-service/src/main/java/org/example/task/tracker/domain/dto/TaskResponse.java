package org.example.task.tracker.domain.dto;

import java.util.Date;
import java.util.UUID;

public class TaskResponse {

    private UUID uuid;
    private String description ;
    private String status ;
    private Date createdAt ;
    private Date updatedAt ;

    public TaskResponse() {
    }

    public TaskResponse(UUID uuid, String description, String status, Date createdAt, Date updatedAt) {
        this.uuid = uuid;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
