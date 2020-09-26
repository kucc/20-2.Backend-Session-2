package com.todo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
