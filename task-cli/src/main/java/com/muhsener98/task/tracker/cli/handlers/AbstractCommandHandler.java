package com.muhsener98.task.tracker.cli.handlers;

import com.muhsener98.task.tracker.cli.IOMapper;
import com.muhsener98.task.tracker.cli.InputValidator;
import org.example.task.tracker.controller.TaskCliController;

public abstract class AbstractCommandHandler {

    protected final TaskCliController taskCliController;
    protected final InputValidator inputValidator;
    protected  final IOMapper ioMapper ;


    public AbstractCommandHandler(TaskCliController taskCliController,
                                  InputValidator inputValidator,
                                  IOMapper ioMapper) {
        this.taskCliController = taskCliController;
        this.inputValidator = inputValidator;
        this.ioMapper = ioMapper;
    }


    public abstract void  execute(String[] args);

}
