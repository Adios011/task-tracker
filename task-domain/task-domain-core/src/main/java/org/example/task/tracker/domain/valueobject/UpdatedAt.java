package org.example.task.tracker.domain.valueobject;

import java.util.Date;
import java.util.Objects;

public class UpdatedAt {

    private final Date date;

    public UpdatedAt(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdatedAt updatedAt = (UpdatedAt) o;
        return Objects.equals(date, updatedAt.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
