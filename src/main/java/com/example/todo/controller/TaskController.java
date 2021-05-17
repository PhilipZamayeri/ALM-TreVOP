package com.example.todo.controller;

import com.example.todo.models.Task;
import com.example.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Philip Zamayeri
 * Date: 2021-05-17
 * Time: 13:35
 * Project: todo
 * Copyright: MIT
 */
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService service;

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return service.getTasks();
    }

    @PostMapping("/tasks")
    public void saveNewTask(@RequestBody Task task) {
        service.saveTask(task);
    }

    @GetMapping("/tasks/active")
    public List<String> getActiveTasks() {
        return service.getActiveTasks(true);
    }

    @GetMapping("/tasks/notActive")
    public List<String> getNotActiveTasks() {
        return service.getActiveTasks(false);
    }

    @PostMapping("/tasks/update/{id}")
    public String updateStatus(@PathVariable String id){
        service.updateStatus(id);
        return "sucess";
    }


}


