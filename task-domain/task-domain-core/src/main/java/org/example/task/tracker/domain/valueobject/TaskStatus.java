package org.example.task.tracker.domain.valueobject;

public enum TaskStatus {
    TODO, IN_PROGRESS, DONE;

    public static TaskStatus fromString(String str) throws RuntimeException {
        if (str.equalsIgnoreCase(TODO.name()))
            return TODO;
        else if (str.equalsIgnoreCase(IN_PROGRESS.name()))
            return IN_PROGRESS;
        else if (str.equalsIgnoreCase(DONE.name()))
            return DONE;
        else
            throw new RuntimeException("unknown status type: " + str);
    }
}
