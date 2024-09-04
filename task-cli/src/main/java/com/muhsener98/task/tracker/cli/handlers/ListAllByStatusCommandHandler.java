package com.muhsener98.task.tracker.cli.handlers;

import com.muhsener98.task.tracker.cli.IOMapper;
import com.muhsener98.task.tracker.cli.InputValidator;
import com.muhsener98.task.tracker.cli.TableViewPrinter;
import org.example.task.tracker.controller.TaskCliController;
import org.example.task.tracker.domain.dto.TaskResponse;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;

import java.util.List;

public class ListAllByStatusCommandHandler extends AbstractCommandHandler{
    public ListAllByStatusCommandHandler(TaskCliController taskCliController, InputValidator inputValidator, IOMapper ioMapper) {
        super(taskCliController, inputValidator, ioMapper);
    }

    @Override
    public void execute(String[] args) {
        super.inputValidator.validateListAllByStatusArgument(args);

        List<TaskResponse> taskResponseList ;

        try{
            taskResponseList = taskCliController.listAllByStatus(args[1]);
            TableViewPrinter.printTableView(taskResponseList);
        }catch (DataAccessException exception){
            System.err.println("Unknown internal error!");
            System.exit(0);;
        }


    }
}
