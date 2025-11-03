package com.todolist.todolist.controller;

import com.todolist.todolist.dto.TodoRequestDTO;
import com.todolist.todolist.dto.TodoResponseDTO;
import com.todolist.todolist.exception.TodoNotFoundException;
import com.todolist.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoResponseDTO> findAll() {
        return todoService.listAll();
    }

    @GetMapping("/{id}")
    public TodoResponseDTO findById(@PathVariable Long id) throws TodoNotFoundException {
        return todoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponseDTO add(@Valid @RequestBody TodoRequestDTO dto) {
        return todoService.create(dto);
    }

    @PutMapping("/{id}")
    public TodoResponseDTO update(@PathVariable Long id, @Valid @RequestBody TodoRequestDTO dto) throws TodoNotFoundException {
        return todoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }

    @GetMapping("/concluded/{concluded}")
    public List<TodoResponseDTO> listByConcluded(@PathVariable boolean concluded) {
        return todoService.listByConcluded(concluded);
    }
}