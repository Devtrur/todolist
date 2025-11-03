package com.todolist.todolist.controller;

import com.todolist.todolist.dto.TodoRequestDTO;
import com.todolist.todolist.dto.TodoResponseDTO;
import com.todolist.todolist.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todolist")
@Tag(name = "TodoList", description = "Endpoints para gerenciar tarefas")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as tarefas", description = "Retorna lista paginada de todas as tarefas")
    public Page<TodoResponseDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return todoService.listAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica pelo ID")
    public TodoResponseDTO findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar nova tarefa", description = "Cria uma nova tarefa")
    public TodoResponseDTO add(@Valid @RequestBody TodoRequestDTO dto) {
        return todoService.create(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tarefa", description = "Atualiza uma tarefa existente")
    public TodoResponseDTO update(@PathVariable Long id, @Valid @RequestBody TodoRequestDTO dto) {
        return todoService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar tarefa", description = "Remove uma tarefa pelo ID")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }

    @GetMapping("/concluded/{concluded}")
    @Operation(summary = "Filtrar por status", description = "Lista tarefas filtradas por status de conclusão")
    public Page<TodoResponseDTO> listByConcluded(
            @PathVariable boolean concluded,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return todoService.listByConcluded(concluded, page, size);
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar tarefas", description = "Busca tarefas por status e título")
    public Page<TodoResponseDTO> searchByConcludedAndTitle(
            @RequestParam boolean concluded,
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return todoService.listByConcludedAndTitle(concluded, title, page, size);
    }
}