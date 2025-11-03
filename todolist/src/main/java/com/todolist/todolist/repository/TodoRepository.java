package com.todolist.todolist.repository;

import com.todolist.todolist.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findByTitleContainingIgnoreCase(String title);
    List<TodoEntity> findByConcludedOrderByCreatedAtDesc(boolean concluded);
    List<TodoEntity> findByConcludedAndTitleContaining(boolean concluded, String title);
}
