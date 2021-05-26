package com.example.todo.controller;

import com.example.todo.models.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    TaskRepository mockRepo;

    @BeforeEach
    void init(){
        Task t1 = new Task("1","test1",true);
        Task t2 = new Task("2","test2",true);
        Task t3 = new Task("3","test3",false);

        when(mockRepo.findAll()).thenReturn(Arrays.asList(t1,t2,t3));
        when(mockRepo.findTaskByActive(true)).thenReturn(Arrays.asList(t1,t2));
        when(mockRepo.findTaskByActive(false)).thenReturn(Arrays.asList(t3));
    }


    @Test
    void getTasks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tasks").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"description\":\"test1\",\"active\":true},{\"id\":\"2\",\"description\":\"test2\",\"active\":true},{\"id\":\"3\",\"description\":\"test3\",\"active\":false}]"));
    }

    @Test
    void getActiveTasks() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/tasks/active").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"description\":\"test1\",\"active\":true},{\"id\":\"2\",\"description\":\"test2\",\"active\":true}]"));
    }

    @Test
    void getNotActiveTasks() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/tasks/notActive").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"3\",\"description\":\"test3\",\"active\":false}]"));
    }

    @Test
    void saveNewTask() throws Exception {
        Task testTask = new Task("TestTask", true);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(testTask);

        mvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Task saved"));
    }

    @Test
    void saveNewTaskInvalid() throws Exception {
        Task testTask = new Task();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(testTask);

        mvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON).content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid task"));
    }


    @Test
    void updateStatusFail() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/tasks/update/9").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("The task with id: 9 dont exist")));
    }
}