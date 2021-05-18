package com.example.todo.service;

import com.example.todo.models.Task;
import com.example.todo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Philip Zamayeri
 * Date: 2021-05-18
 * Time: 09:21
 * Project: todo
 * Copyright: MIT
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @BeforeEach
    void init(){
        taskService = new TaskService(taskRepository);
    }

    @Test
    void getTasks() {
        Task task = new Task();
        task.setDescription("Spaghetti");

        List<Task> expected = Arrays.asList(task);
        when(taskRepository.findAll()).thenReturn(expected);

        List<Task> actual = taskService.getTasks();
        assertEquals(expected, actual);
        verify(taskRepository,times(1)).findAll();
    }

    @Test
    void saveTask() {
        Task task = new Task();
        task.setDescription("Meatballs");

        when(taskRepository.save(task)).thenReturn(task);

        Task task2 = new Task();
        task2.setDescription("Meatballs");

        assertEquals(task, taskService.saveTask(task2));
        verify(taskRepository,times(1)).save(any());
    }
}