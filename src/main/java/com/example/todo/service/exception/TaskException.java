package com.example.todo.service.exception;

/**
 * Created by Emil Johansson
 * Date: 2021-05-18
 * Time: 11:56
 * Project: todo
 * Package: com.example.todo.service.exception
 */
public class TaskException extends RuntimeException{
    public TaskException(String errorMessage) {
        super(errorMessage);
    }
}
