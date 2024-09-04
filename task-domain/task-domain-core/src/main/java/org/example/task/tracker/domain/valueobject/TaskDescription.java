package org.example.task.tracker.domain.valueobject;

import java.util.Objects;

public class TaskDescription {

    private final String value ;

    public TaskDescription(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean valid() {
        return value != null && value.length() >= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDescription that = (TaskDescription) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
