package org.example.task.tracker.dataaccess.jsonfile.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class TaskJsonEntity {

    private UUID id;
    private String description;
    private String status;

    private Date createdAt;

    private Date updatedAt;

    public TaskJsonEntity() {
    }

    public TaskJsonEntity(UUID id, String description, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"id\":\"").append(id)
                .append("\",\"description\":\"").append(description)
                .append("\",\"status\":\"").append(status)
                .append("\",\"createdAt\":\"").append(createdAt)
                .append("\",\"updatedAt\":\"").append(updatedAt)
                .append("\"}");
        return sb.toString();
    }
}
