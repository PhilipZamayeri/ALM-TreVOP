package com.example.todo.repository;

import com.example.todo.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findTaskByActive(boolean active);
}
