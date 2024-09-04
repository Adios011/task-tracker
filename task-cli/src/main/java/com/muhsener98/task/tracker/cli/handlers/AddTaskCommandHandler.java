package com.muhsener98.task.tracker.cli.handlers;

import com.muhsener98.task.tracker.cli.CommandSuccessMessages;
import com.muhsener98.task.tracker.cli.IOMapper;
import com.muhsener98.task.tracker.cli.InputValidator;
import org.example.task.tracker.controller.TaskCliController;
import org.example.task.tracker.dataaccess.jsonfile.exception.TaskJsonDataAccessException;
import org.example.task.tracker.domain.dto.CreateTaskRequest;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.exception.TaskDomainException;

public class AddTaskCommandHandler extends AbstractCommandHandler {


    public AddTaskCommandHandler(TaskCliController taskCliController, InputValidator inputValidator, IOMapper ioMapper) {
        super(taskCliController, inputValidator, ioMapper);
    }

    @Override
    public void execute(String[] args) {
        inputValidator.validateAddArgument(args);
        CreateTaskRequest request = super.ioMapper.createTaskRequest(args[1]);

        TaskResponse response ;

        try {
            response =  super.taskCliController.addTask(request);
            System.out.println(CommandSuccessMessages.ADD_SUCCESS + response.getUuid().toString());
        }catch (TaskDomainException e){
            System.err.println(e.getMessage());
        }catch (TaskJsonDataAccessException exception){
            System.err.println("unknown internal error.");
        }


    }
}
