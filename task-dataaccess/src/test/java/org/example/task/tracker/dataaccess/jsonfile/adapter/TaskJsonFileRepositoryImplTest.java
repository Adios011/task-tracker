package org.example.task.tracker.dataaccess.jsonfile.adapter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.task.tracker.dataaccess.jsonfile.mapper.TaskDataAccessMapper;
import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.valueobject.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class TaskJsonFileRepositoryImplTest {

    private TaskJsonFileRepositoryImpl taskJsonFileRepository;

    private Task task;
    private final UUID taskUUID = UUID.randomUUID();
    private final TaskId taskId = new TaskId(taskUUID);
    private final String descriptionString = "task-description-1";
    private final TaskDescription taskDescription = new TaskDescription(descriptionString);
    private final Date createdAtDate = new Date();
    private final CreatedAt createdAt = new CreatedAt(createdAtDate);
    private final Date updatedAtDate = new Date();
    private final UpdatedAt updatedAt = new UpdatedAt(updatedAtDate);
    private final String taskStatusString = "TODO";
    private final TaskStatus taskStatus = TaskStatus.fromString(taskStatusString);

    private final String tempTestFileDirectory = "/Users/muhammetsener/Desktop/roadmap-projects/task-tracker/task-dataaccess/test.json";



    @BeforeEach
    public void init(){
        TaskDataAccessMapper dataAccessMapper = new TaskDataAccessMapper();
        ObjectMapper objectMapper = new ObjectMapper();
        taskJsonFileRepository  = new TaskJsonFileRepositoryImpl(dataAccessMapper , objectMapper);
        taskJsonFileRepository.setFileDirectory(tempTestFileDirectory);

        task = Task.Builder.builder()
                .id(taskId)
                .description(taskDescription)
                .status(taskStatus)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @AfterAll
    public static void deleteFile() {
        File file = new File("/Users/muhammetsener/Desktop/roadmap-projects/task-tracker/task-dataaccess/test.json");
        if (file.exists()) {
            if (!file.delete()) {
                System.err.println("Error deleting file: " + file.getAbsolutePath());
            }
        }
    }

    @Test
    public void test_save_success(){
        taskJsonFileRepository.save(task);

        Optional<Task> optional = taskJsonFileRepository.findById(taskId);
        assertTrue(optional.isPresent());
        Task savedTask = optional.get();
        assertEquals(taskId , savedTask.getId());
        assertEquals(descriptionString , savedTask.getDescription().getValue());
    }


    @Test
    public void test_findAll_SUCCESS(){
        List<Task>  tasks = taskJsonFileRepository.findAll();
        assertTrue(tasks.size() > 0);
    }

    @Test
    public void test_update_success(){
        taskJsonFileRepository.save(task);

        Task newTask = Task.Builder.builder()
                .id(taskId)
                .description(new TaskDescription("I have changed description"))
                .updatedAt(updatedAt)
                .createdAt(createdAt)
                .status(TaskStatus.DONE)
                .build();

        taskJsonFileRepository.update(newTask);
        Task updatedTask = taskJsonFileRepository.findById(taskId).get();

        assertEquals(updatedTask.getDescription().getValue() , newTask.getDescription().getValue());
    }


    @Test
    public void testUpdate_NoSuchTask_throwsException(){
        Task newTask = Task.Builder.builder()
                .id(new TaskId(UUID.randomUUID()))
                .description(new TaskDescription("I have changed description"))
                .updatedAt(updatedAt)
                .createdAt(createdAt)
                .status(TaskStatus.DONE)
                .build();

        RuntimeException exception  = assertThrows(RuntimeException.class ,
                () -> taskJsonFileRepository.update(newTask));

        assertEquals(exception.getMessage() , "No such task exists " + newTask.getId().getValue());


    }


}
