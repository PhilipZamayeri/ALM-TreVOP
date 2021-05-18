package com.example.todo.service;

import com.example.todo.models.Task;
import com.example.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository repository;

    public List<Task> getTasks() {
        return repository.findAll();
    }

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public List<String> getActiveTasks(Boolean bool) {

        List<Task> activeTasks = repository.findTaskByActive(bool);

        return activeTasks.stream()
                .map(Task::getDescription)
                .collect(Collectors.toList());
    }

    public void updateStatus(String id){
        Optional<Task> task = repository.findById(id);
        task.ifPresent(value -> value.setActive(!value.isActive()));
        repository.save(task.get());
    };
}
