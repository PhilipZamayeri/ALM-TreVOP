package com.example.todo.controller;

import com.example.todo.models.Task;
import com.example.todo.service.TaskService;
import com.example.todo.service.exception.TaskDoesNotExist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService service;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTasks());
    }

    @PostMapping("/tasks")
    public ResponseEntity<String> saveNewTask(@RequestBody Task task) {
        service.saveTask(task);
        return ResponseEntity.status(HttpStatus.OK).body("Task saved");
    }

    @GetMapping("/tasks/active")
    public ResponseEntity<List<String>> getActiveTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getActiveTasks(true));
    }

    @GetMapping("/tasks/notActive")
    public ResponseEntity<List<String>> getNotActiveTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getActiveTasks(false));
    }

    @PostMapping("/tasks/update/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable String id){
       try{
           return ResponseEntity.status(HttpStatus.OK).body(service.updateStatus(id));
       } catch (TaskDoesNotExist exception) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
       }
    }


}


