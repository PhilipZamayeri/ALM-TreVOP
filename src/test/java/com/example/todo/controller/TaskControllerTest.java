package com.example.todo.controller;

import com.example.todo.models.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    TaskController taskController;


    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @BeforeEach
    void init(){
        taskService = new TaskService(taskRepository);
        taskController = new TaskController(taskService);
    }


    @Test
    void getTasks() {
        taskController.getTasks();
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void saveNewTask() {
        Task mockTask = new Task();
        mockTask.setDescription("Baka bullar");
        mockTask.setActive(true);

        taskController.saveNewTask(mockTask);
        verify(taskRepository, times(1)).save(mockTask);
    }

    @Test
    void updateStatus() {
    }
}