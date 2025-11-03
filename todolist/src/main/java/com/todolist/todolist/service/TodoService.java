package com.todolist.todolist.service;

import com.todolist.todolist.dto.TodoRequestDTO;
import com.todolist.todolist.dto.TodoResponseDTO;
import com.todolist.todolist.entity.TodoEntity;
import com.todolist.todolist.exception.TodoNotFoundException;
import com.todolist.todolist.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TodoResponseDTO> listAll() {
        return todoRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, TodoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public TodoResponseDTO findById(Long id) throws TodoNotFoundException {
        TodoEntity entity = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));
        return modelMapper.map(entity, TodoResponseDTO.class);
    }

    public TodoResponseDTO create(TodoRequestDTO dto) {
        TodoEntity entity = modelMapper.map(dto, TodoEntity.class);
        TodoEntity saved = todoRepository.save(entity);
        return modelMapper.map(saved, TodoResponseDTO.class);
    }

    public TodoResponseDTO update(Long id, TodoRequestDTO dto) throws TodoNotFoundException {
        TodoEntity entity = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setConcluded(dto.isConcluded());

        TodoEntity updated = todoRepository.save(entity);
        return modelMapper.map(updated, TodoResponseDTO.class);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    public List<TodoResponseDTO> listByConcluded(boolean concluded) {
        return todoRepository.findByConcludedOrderByCreatedAtDesc(concluded).stream()
                .map(entity -> modelMapper.map(entity, TodoResponseDTO.class))
                .collect(Collectors.toList());
    }
}