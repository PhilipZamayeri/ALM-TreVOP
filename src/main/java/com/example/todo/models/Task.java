package com.example.todo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    String id;
    String description;
    boolean active;

    public Task(String description, boolean active) {
        this.description = description;
        this.active = active;
    }
}
