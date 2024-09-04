package org.example.task.tracker.dataaccess.jsonfile.exception;

import org.example.task.tracker.domain.ports.output.repository.DataAccessException;

public class TaskJsonDataAccessException extends DataAccessException {

    public TaskJsonDataAccessException(String message) {
        super(message);
    }
}
