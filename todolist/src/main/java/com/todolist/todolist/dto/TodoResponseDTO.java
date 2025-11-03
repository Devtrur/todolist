package com.todolist.todolist.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoResponseDTO {

    private Long id;
    private String title;
    private String description;
    private boolean concluded;
    private LocalDateTime createdAt;
}
