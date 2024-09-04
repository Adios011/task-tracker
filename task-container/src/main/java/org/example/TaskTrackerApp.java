package org.example;

import com.muhsener98.task.tracker.cli.dispatcher.ControllerDispatcher;
import com.muhsener98.service.locator.ServiceLocatorFactory;
import org.example.task.tracker.container.BeanConfiguration;


public class TaskTrackerApp {
    public static void main(String[] args) {
        BeanConfiguration.configure();
        ControllerDispatcher controllerDispatcher = (ControllerDispatcher) ServiceLocatorFactory.getInstance().getService(ControllerDispatcher.class);
        controllerDispatcher.dispatch(args);
    }
}
