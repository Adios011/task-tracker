package org.example.task.tracker.domain.valueobject;

import java.util.Date;

public class CreatedAt {

    private final Date date;

    public CreatedAt(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
