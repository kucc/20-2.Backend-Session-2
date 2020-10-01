package com.todo.demo.repository;

import com.todo.demo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
