package org.example.task.tracker.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muhsener98.service.locator.ServiceLocator;
import com.muhsener98.service.locator.ServiceLocatorFactory;
import com.muhsener98.task.tracker.cli.*;
import com.muhsener98.task.tracker.cli.dispatcher.ControllerDispatcher;
import com.muhsener98.task.tracker.cli.handlers.*;
import org.example.task.tracker.controller.TaskCliController;
import org.example.task.tracker.dataaccess.jsonfile.adapter.TaskJsonFileRepositoryImpl;
import org.example.task.tracker.dataaccess.jsonfile.mapper.TaskDataAccessMapper;
import org.example.task.tracker.domain.*;
import org.example.task.tracker.domain.mapper.TaskDataMapper;
import org.example.task.tracker.domain.ports.input.TaskApplicationService;
import org.example.task.tracker.domain.ports.output.repository.TaskRepository;
import org.example.task.tracker.domain.service.TaskDomainService;
import org.example.task.tracker.domain.service.impl.TaskDomainServiceImpl;

public class BeanConfiguration {

    public static void configure() {

        ServiceLocator locator = ServiceLocatorFactory.getInstance();
        locator.registerService(TaskRepository.class, taskRepository());
        locator.registerService(TaskApplicationService.class, taskApplicationService());
        locator.registerService(TaskCliController.class, taskController());
        locator.registerService(ControllerDispatcher.class, controllerDispatcher());

    }

    public static TaskCliController taskController() {
        return new TaskCliController(taskApplicationService());
    }

    public static ControllerDispatcher controllerDispatcher() {
        return new ControllerDispatcher(addTaskCommandHandler(),
                deleteTaskCommandHandler(), listAllCommandHandler(), updateCommandHandler()
                , markInProgressCommandHandler(), markDoneCommandHandler(), listAllByStatusCommandHandler(), inputValidator()
        );
    }

    public static ListAllByStatusCommandHandler listAllByStatusCommandHandler() {
        return new ListAllByStatusCommandHandler(taskCliController(), inputValidator(), ioMapper());
    }

    public static MarkDoneCommandHandler markDoneCommandHandler() {
        return new MarkDoneCommandHandler(taskCliController(), inputValidator(), ioMapper());
    }

    public static UpdateCommandHandler updateCommandHandler() {
        return new UpdateCommandHandler(taskCliController(), inputValidator(), ioMapper());
    }

    public static ListAllCommandHandler listAllCommandHandler() {
        return new ListAllCommandHandler(taskCliController(), inputValidator(), ioMapper());
    }

    public static DeleteTaskUseCase deleteTaskUseCase() {
        return new DeleteTaskUseCase(taskDataMapper(), taskRepository(), taskDomainService());
    }

    public static MarkInProgressCommandHandler markInProgressCommandHandler() {
        return new MarkInProgressCommandHandler(taskCliController(), inputValidator(), ioMapper());
    }


    public static AddTaskCommandHandler addTaskCommandHandler() {
        return new AddTaskCommandHandler(taskCliController(), inputValidator(), ioMapper());
    }

    public static DeleteTaskCommandHandler deleteTaskCommandHandler() {
        return new DeleteTaskCommandHandler(taskCliController(), inputValidator(), ioMapper());
    }

    public static IOMapper ioMapper() {
        return new IOMapper();
    }

    public static InputValidator inputValidator() {
        return new InputValidator();
    }

    public static TaskCliController taskCliController() {
        return new TaskCliController(taskApplicationService());
    }

    public static TaskApplicationService taskApplicationService() {
        return new TaskApplicationServiceImpl(createTaskUseCase(), deleteTaskUseCase(), listAllTasksUseCase(), updateTaskUseCase(), listAllTasksByStatusUseCase());
    }

    public static ListAllTasksByStatusUseCase listAllTasksByStatusUseCase() {
        return new ListAllTasksByStatusUseCase(taskRepository(), taskDataMapper());
    }

    public static UpdateTaskUseCase updateTaskUseCase() {
        return new UpdateTaskUseCase(taskDomainService(), taskRepository(), taskDataMapper());
    }

    public static ListAllTasksUseCase listAllTasksUseCase() {
        return new ListAllTasksUseCase(taskDataMapper(), taskRepository());
    }

    public static CreateTaskUseCase createTaskUseCase() {
        return new CreateTaskUseCase(taskDataMapper(), taskDomainService(), taskRepository());
    }

    public static TaskDataMapper taskDataMapper() {
        return new TaskDataMapper();
    }


    public static TaskDomainService taskDomainService() {
        return new TaskDomainServiceImpl();
    }

    public static TaskJsonFileRepositoryImpl taskRepository() {
        return new TaskJsonFileRepositoryImpl(taskDataAccessMapper(), objectMapper());
    }

    public static TaskDataAccessMapper taskDataAccessMapper() {
        return new TaskDataAccessMapper();
    }

    public static ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
