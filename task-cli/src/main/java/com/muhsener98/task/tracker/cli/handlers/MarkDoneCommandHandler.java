package com.muhsener98.task.tracker.cli.handlers;

import com.muhsener98.task.tracker.cli.IOMapper;
import com.muhsener98.task.tracker.cli.InputValidator;
import org.example.task.tracker.controller.TaskCliController;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.dto.UpdateTaskCommand;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;

import java.util.UUID;

public class MarkDoneCommandHandler extends AbstractCommandHandler {

    public MarkDoneCommandHandler(TaskCliController taskCliController, InputValidator inputValidator, IOMapper ioMapper) {
        super(taskCliController, inputValidator, ioMapper);
    }

    @Override
    public void execute(String[] args) {
        super.inputValidator.validateMarkDoneArgument(args);

        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(UUID.fromString(args[1]), null, "done");

        TaskResponse taskResponse;
        try {
            taskResponse = taskCliController.updateTask(updateTaskCommand);
            System.out.println("Task updated successfully with ID: " + taskResponse.getUuid().toString());
        } catch (DataAccessException exception) {
            System.err.println("Unknown internal error!");
            System.exit(0);
        }
    }
}
