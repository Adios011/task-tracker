package com.muhsener98.task.tracker.cli;

public enum InputCommands {

    ADD("add"),
    DELETE("delete"),
    LIST("list"),
    UPDATE("update"),
    MARK_IN_PROGRESS("mark-in-progress"),
    MARK_DONE("mark-done");


    private String value;

    InputCommands(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
