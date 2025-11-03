package com.todolist.todolist.service;

import com.todolist.todolist.dto.TodoRequestDTO;
import com.todolist.todolist.dto.TodoResponseDTO;
import com.todolist.todolist.entity.TodoEntity;
import com.todolist.todolist.exception.TodoNotFoundException;
import com.todolist.todolist.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public TodoService(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    public Page<TodoResponseDTO> listAll(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable)
                .map(entity -> modelMapper.map(entity, TodoResponseDTO.class));
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

    public Page<TodoResponseDTO> listByConcluded(boolean concluded, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return todoRepository.findByConcludedOrderByCreatedAtDesc(concluded, pageable)
                .map(entity -> modelMapper.map(entity, TodoResponseDTO.class));
    }

    public Page<TodoResponseDTO> listByConcludedAndTitle(boolean concluded, String title, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return todoRepository.findByConcludedAndTitleContaining(concluded, title, pageable)
                .map(entity -> modelMapper.map(entity, TodoResponseDTO.class));
    }
}
