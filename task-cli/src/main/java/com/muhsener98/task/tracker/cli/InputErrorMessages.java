package com.muhsener98.task.tracker.cli;

public class InputErrorMessages {

    public static final String NO_ARG_ERROR_MESSAGE = "Enter one of the commands [add, update, delete, mark-in-progress, mark-done, list]";
    public static final String NO_ADD_ARGUMENT = "Usage: task-cli add <description>" ;
    public static final String NO_DELETE_ARGUMENT = "Usage: task-cli delete <taskId>";
    public static final String  INVALID_LIST_ALL_ARGUMENT ="Usage: task-cli list [status]" ;
    public static final String INVALID_UPDATE_ARGUMENT = "Usage: task-cli update <taskId> <new-description>";
    public static final String INVALID_MARK_IN_PROGRESS_COMMAND ="Usage: task-cli mark-in-progress <taskId>" ;
    public static final String INVALID_MARK_DONE_ARGUMENT = "Usage: task-cli mark-done <taskId>";
}
