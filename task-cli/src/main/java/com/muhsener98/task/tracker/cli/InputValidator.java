package com.muhsener98.task.tracker.cli;

import org.example.task.tracker.domain.valueobject.TaskStatus;

import java.util.UUID;

public class InputValidator {

    public void validateArgumentNumber(String[] args) {
        if (args.length == 0) {
            System.err.println(InputErrorMessages.NO_ARG_ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void validateAddArgument(String[] args) {
        if (args.length < 2 || args[1].isEmpty()) {
            System.err.println(InputErrorMessages.NO_ADD_ARGUMENT);
            System.exit(0);
        }
    }

    public void validateDeleteArgument(String[] args) {
        if (args.length < 2 || args[1].isEmpty()) {
            System.out.println(InputErrorMessages.NO_DELETE_ARGUMENT);
            System.exit(0);

        }
        validateUUID(args);
    }

    public void validateListArgument(String[] args) {
        if (args.length != 1) {
            System.out.println(InputErrorMessages.INVALID_LIST_ALL_ARGUMENT);
            System.exit(0);
        }
    }

    public void validateUpdateArgument(String[] args) {
        if (args.length < 3 || args[1].isEmpty() || args[2].isEmpty()) {
            System.out.println(InputErrorMessages.INVALID_UPDATE_ARGUMENT);
            System.exit(0);
        }
        validateUUID(args);
    }

    public void validateMarkInProgressArgument(String[] args) {
        if (args.length < 2 || args[1].isEmpty()) {
            System.out.println(InputErrorMessages.INVALID_MARK_IN_PROGRESS_COMMAND);
            System.exit(0);
        }
        validateUUID(args);
    }


    public void validateMarkDoneArgument(String[] args) {
        if (args.length < 2 || args[1].isEmpty()) {
            System.err.println(InputErrorMessages.INVALID_MARK_DONE_ARGUMENT);
            System.exit(0);
        }

        validateUUID(args);



    }


    private void validateUUID(String... args) {
        String uuid = args[1];
        try {
            UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid UUID");
            System.exit(0);
        }
    }


    public void validateListAllByStatusArgument(String[] args) {
        if(args.length < 2 || args[1].isEmpty()){
            System.err.println(InputErrorMessages.INVALID_LIST_ALL_ARGUMENT);
            System.exit(0);
        }

        String status = args[1];
        validateStatusInput(status);

    }

    private void validateStatusInput(String status){
        try{
            TaskStatus.fromString(status);
        }catch (RuntimeException exception){
            System.err.println("Usage: task-cli list [todo or in_progress or done]");
            System.exit(0);
        }
    }
}
