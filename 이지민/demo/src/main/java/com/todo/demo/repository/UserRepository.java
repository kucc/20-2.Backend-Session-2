package com.todo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.demo.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findByUsername(String name);
}
