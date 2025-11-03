package com.todolist.todolist.controller;

import com.todolist.todolist.dto.TodoRequestDTO;
import com.todolist.todolist.dto.TodoResponseDTO;
import com.todolist.todolist.exception.TodoNotFoundException;
import com.todolist.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todolist")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public Page<TodoResponseDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return todoService.listAll(page, size);
    }

    @GetMapping("/{id}")
    public TodoResponseDTO findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponseDTO add(@Valid @RequestBody TodoRequestDTO dto) {
        return todoService.create(dto);
    }

    @PutMapping("/{id}")
    public TodoResponseDTO update(@PathVariable Long id, @Valid @RequestBody TodoRequestDTO dto) {
        return todoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }

    @GetMapping("/concluded/{concluded}")
    public Page<TodoResponseDTO> listByConcluded(
            @PathVariable boolean concluded,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return todoService.listByConcluded(concluded, page, size);
    }

    @GetMapping("/search")
    public Page<TodoResponseDTO> searchByConcludedAndTitle(
            @RequestParam boolean concluded,
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return todoService.listByConcludedAndTitle(concluded, title, page, size);
    }
}
