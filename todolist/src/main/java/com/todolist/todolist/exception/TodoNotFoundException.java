package com.todolist.todolist.exception;

public class TodoNotFoundException extends Exception {

    public TodoNotFoundException(Long id) {
        super("Todo not found with id " + id);
    }

    public TodoNotFoundException(String message) {
        super(message);
    }
}
