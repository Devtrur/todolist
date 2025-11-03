package com.todolist.todolist.repository;

import com.todolist.todolist.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findByTitleContainingIgnoreCase(String title);

    List<TodoEntity> findByConcludedOrderByCreatedAtDesc(boolean concluded);

    List<TodoEntity> findByConcludedAndTitleContaining(boolean concluded, String title);

    @Override
    Page<TodoEntity> findAll(Pageable pageable);

    Page<TodoEntity> findByConcludedOrderByCreatedAtDesc(boolean concluded, Pageable pageable);

    Page<TodoEntity> findByConcludedAndTitleContaining(boolean concluded, String title, Pageable pageable);
}
