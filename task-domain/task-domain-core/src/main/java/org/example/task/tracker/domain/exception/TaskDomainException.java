package org.example.task.tracker.domain.exception;

public class TaskDomainException extends RuntimeException{

    public TaskDomainException(String message) {
        super(message);
    }
}
