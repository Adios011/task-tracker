package org.example.task.tracker.dataaccess.jsonfile.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.task.tracker.dataaccess.jsonfile.entity.TaskJsonEntity;
import org.example.task.tracker.dataaccess.jsonfile.exception.TaskJsonDataAccessException;
import org.example.task.tracker.dataaccess.jsonfile.mapper.TaskDataAccessMapper;
import org.example.task.tracker.domain.entity.Task;
import org.example.task.tracker.domain.ports.output.repository.DataAccessException;
import org.example.task.tracker.domain.ports.output.repository.TaskRepository;
import org.example.task.tracker.domain.valueobject.TaskId;
import org.example.task.tracker.domain.valueobject.TaskStatus;

import java.io.*;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class TaskJsonFileRepositoryImpl implements TaskRepository {

    private final TaskDataAccessMapper taskDataAccessMapper;
    private final ObjectMapper objectMapper;

    private String FILE_DIRECTORY;

    {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/Users/muhammetsener/Desktop/roadmap-projects/task-tracker/task-dataaccess/src/main/resources/application.properties"));
            String property = properties.getProperty("datasource.json.file.directory");
            if (property == null)
                throw new RuntimeException("No such properties file found: application.properties in module data-access");

            FILE_DIRECTORY = property;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TaskJsonFileRepositoryImpl(TaskDataAccessMapper taskDataAccessMapper,
                                      ObjectMapper objectMapper) {
        this.taskDataAccessMapper = taskDataAccessMapper;
        this.objectMapper = objectMapper;
        objectMapper.setDateFormat(DateFormat.getDateTimeInstance());
    }

    @Override
    public void save(Task task) throws TaskJsonDataAccessException {
        TaskJsonEntity jsonEntity = taskDataAccessMapper.toJsonEntity(task);

        List<TaskJsonEntity> tasks = readAllFromJsonFile();

        //add new task
        tasks.add(jsonEntity);

        // write back array to json file
        writeToList(tasks);


    }

    @Override
    public Optional<Task> findById(TaskId taskId) {
        List<TaskJsonEntity> tasks = readAllFromJsonFile();

        Optional<TaskJsonEntity> optional = tasks.stream()
                .filter(task -> task.getId().equals(taskId.getValue()))
                .findFirst();
        if (optional.isEmpty())
            return Optional.empty();

        TaskJsonEntity targetTask = optional.get();

        return Optional.of(taskDataAccessMapper.toDomainEntity(targetTask));


    }

    @Override
    public List<Task> findAll() throws TaskJsonDataAccessException {
        List<TaskJsonEntity> jsonEntities = readAllFromJsonFile();

        return taskDataAccessMapper.toDomainEntity(jsonEntities);

    }

    @Override
    public Task update(Task task) {
        List<TaskJsonEntity> taskJsonEntities = readAllFromJsonFile();
        Optional<TaskJsonEntity> optional = taskJsonEntities.stream()
                .filter(taskJsonEntity -> taskJsonEntity.getId().equals(task.getId().getValue())).findFirst();

        if(optional.isEmpty())
            throw new RuntimeException("No such task exists " + task.getId().getValue());

        TaskJsonEntity newEntity = taskDataAccessMapper.toJsonEntity(task);
        taskDataAccessMapper.copy(newEntity , optional.get());

        writeToList(taskJsonEntities);

        return task;
    }

    @Override
    public boolean delete(TaskId taskId) throws TaskJsonDataAccessException {
        List<TaskJsonEntity> taskJsonEntities = readAllFromJsonFile();
        boolean removed =   taskJsonEntities.removeIf(taskJsonEntity -> taskJsonEntity.getId().equals(taskId.getValue()));

        if(removed)
             writeToList(taskJsonEntities);
        else
            throw new TaskJsonDataAccessException("No such task found " + taskId.getValue());

        return true;
    }

    @Override
    public List<Task> findAllByStatus(TaskStatus taskStatus) {
        List<TaskJsonEntity> taskJsonEntities = readAllFromJsonFile();

        List<TaskJsonEntity> filteredTasks = taskJsonEntities.stream()
                .filter(taskJsonEntity -> taskJsonEntity.getStatus().equalsIgnoreCase(taskStatus.toString()))
                .toList();

        return taskDataAccessMapper.toDomainEntity(filteredTasks);
    }


    private File prepareFile(){
        File file = new File(FILE_DIRECTORY);
        try {
            if (!file.exists()) {
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]");
                }
            }else{
                if(file.length() == 0){
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write("[]");
                    }
                }
            }
        } catch (IOException e) {
            throw new  TaskJsonDataAccessException(e.getMessage()   );
        }
        return file;
    }

    private List<TaskJsonEntity> readAllFromJsonFile() {
        File file = prepareFile();
        try {
            return objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, TaskJsonEntity.class));
        } catch (IOException e) {
            throw new  TaskJsonDataAccessException(e.getMessage()   );
        }
    }

    private void writeToList(List<TaskJsonEntity> tasks) {
        File file = prepareFile();
        try {
            objectMapper.writeValue(file, tasks);
        } catch (IOException e) {
            throw new  TaskJsonDataAccessException(e.getMessage()   );
        }
    }


    public void setFileDirectory(String fileDirectory) {
        this.FILE_DIRECTORY = fileDirectory;
    }
}
