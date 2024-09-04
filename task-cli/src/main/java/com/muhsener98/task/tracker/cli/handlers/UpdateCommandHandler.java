package com.muhsener98.task.tracker.cli.handlers;

import com.muhsener98.task.tracker.cli.IOMapper;
import com.muhsener98.task.tracker.cli.InputValidator;
import org.example.task.tracker.controller.TaskCliController;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.dto.UpdateTaskCommand;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;

import java.util.UUID;

public class UpdateCommandHandler extends AbstractCommandHandler {

    public UpdateCommandHandler(TaskCliController taskCliController, InputValidator inputValidator, IOMapper ioMapper) {
        super(taskCliController, inputValidator, ioMapper);
    }

    @Override
    public void execute(String[] args) {
        super.inputValidator.validateUpdateArgument(args);

        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(UUID.fromString(args[1]), args[2], null);

        TaskResponse response;
        try {
            response = taskCliController.updateTask(updateTaskCommand);
            System.out.println("Task updated successfully with ID: " + response.getUuid());
        } catch (DataAccessException dataAccessException) {
            System.out.println("Unknown internal error");
            System.exit(0);
        }

    }
}
