package com.muhsener98.task.tracker.cli.handlers;

import com.muhsener98.task.tracker.cli.CommandSuccessMessages;
import com.muhsener98.task.tracker.cli.IOMapper;
import com.muhsener98.task.tracker.cli.InputValidator;
import org.example.task.tracker.controller.TaskCliController;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;

import java.util.UUID;

public class DeleteTaskCommandHandler extends AbstractCommandHandler {

    public DeleteTaskCommandHandler(TaskCliController taskCliController, InputValidator inputValidator, IOMapper ioMapper) {
        super(taskCliController, inputValidator, ioMapper);
    }

    @Override
    public void execute(String[] args) {
        super.inputValidator.validateDeleteArgument(args);

        TaskResponse response;

        try {
            response = super.taskCliController.deleteTask(UUID.fromString(args[1]));
            System.out.println(CommandSuccessMessages.DELETE_SUCCESS + response.getUuid());
        } catch (DataAccessException e) {
            System.err.println("unknown internal error");
        }
    }
}
