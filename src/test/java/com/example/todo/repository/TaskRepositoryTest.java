package com.example.todo.repository;

import com.example.todo.models.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void findTaskByActive() {

        Task mockTask = new Task();
        mockTask.setDescription("grocery shopping");
        mockTask.setActive(true);
        taskRepository.save(mockTask);

        List<Task> taskResult = taskRepository.findTaskByActive(true);
        String actual = taskResult.get(0).getDescription();
        String expected = "grocery shopping";
        String notExpected = "take a shower";

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);
    }

    @Test
    void findTaskByNotActive() {
        Task mockTask = new Task();
        mockTask.setDescription("go to the gym");
        mockTask.setActive(false);
        taskRepository.save(mockTask);

        List<Task> taskResult = taskRepository.findTaskByActive(false);
        String actual = taskResult.get(0).getDescription();
        String expected = "go to the gym";
        String notExpected = "take a shower";

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);
    }
}