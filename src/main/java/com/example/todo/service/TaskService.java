package com.example.todo.service;

import com.example.todo.models.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.exception.TaskException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository repository;

    public List<Task> getTasks() {
        return repository.findAll();
    }

    public Task saveTask(Task task) {
        if (task.getDescription() == null){
            throw new TaskException("Invalid task");
        }
        return repository.save(task);
    }

    public List<Task> getActiveTasks(Boolean bool) {
        return repository.findTaskByActive(bool);
    }

    public String updateStatus(String id){
        Optional<Task> task = repository.findById(id);
        if (task.isEmpty())
            throw new TaskException("The task with id: " + id + " dont exist");
        task.ifPresent(value -> value.setActive(!value.isActive()));
        repository.save(task.get());
        return id + ": status was updated";
    };
}
