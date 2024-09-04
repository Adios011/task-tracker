package com.muhsener98.task.tracker.cli.dispatcher;


import com.muhsener98.task.tracker.cli.InputCommands;
import com.muhsener98.task.tracker.cli.InputErrorMessages;
import com.muhsener98.task.tracker.cli.InputValidator;
import com.muhsener98.task.tracker.cli.handlers.*;

public class ControllerDispatcher {


    private final AddTaskCommandHandler addTaskCommandHandler;
    private final DeleteTaskCommandHandler deleteTaskCommandHandler;
    private final ListAllCommandHandler listAllCommandHandler;
    private final UpdateCommandHandler updateCommandHandler;
    private final MarkInProgressCommandHandler markInProgressCommandHandler;
    private final MarkDoneCommandHandler markDoneCommandHandler;
    private final ListAllByStatusCommandHandler listAllByStatusCommandHandler;
    private final InputValidator inputValidator;

    public ControllerDispatcher(AddTaskCommandHandler addTaskCommandHandler,
                                DeleteTaskCommandHandler deleteTaskCommandHandler,
                                ListAllCommandHandler listAllCommandHandler,
                                UpdateCommandHandler updateCommandHandler,
                                MarkInProgressCommandHandler markInProgressCommandHandler,
                                MarkDoneCommandHandler markDoneCommandHandler,
                                ListAllByStatusCommandHandler listAllByStatusCommandHandler,
                                InputValidator inputValidator) {
        this.addTaskCommandHandler = addTaskCommandHandler;
        this.deleteTaskCommandHandler = deleteTaskCommandHandler;
        this.listAllCommandHandler = listAllCommandHandler;
        this.updateCommandHandler = updateCommandHandler;
        this.markInProgressCommandHandler = markInProgressCommandHandler;
        this.markDoneCommandHandler = markDoneCommandHandler;
        this.listAllByStatusCommandHandler = listAllByStatusCommandHandler;
        this.inputValidator = inputValidator;
    }

    public void dispatch(String[] args) {
        inputValidator.validateArgumentNumber(args);

        String command = args[0];

        if (command.equalsIgnoreCase(InputCommands.ADD.getValue())) {
            addTaskCommandHandler.execute(args);
        } else if (command.equalsIgnoreCase(InputCommands.DELETE.getValue())) {
            deleteTaskCommandHandler.execute(args);
        } else if (command.equalsIgnoreCase(InputCommands.LIST.getValue())) {


            if(args.length >= 2){
                listAllByStatusCommandHandler.execute(args);
            }else{
                listAllCommandHandler.execute(args);
            }


        }else if(command.equalsIgnoreCase(InputCommands.UPDATE.getValue())){
            updateCommandHandler.execute(args);
        }else if(command.equalsIgnoreCase(InputCommands.MARK_IN_PROGRESS.getValue())){
            markInProgressCommandHandler.execute(args);
        }else if(command.equalsIgnoreCase(InputCommands.MARK_DONE.getValue())){
            markDoneCommandHandler.execute(args);
        }else {
            System.out.println("Unknown command: " + command);
        }
    }
}
