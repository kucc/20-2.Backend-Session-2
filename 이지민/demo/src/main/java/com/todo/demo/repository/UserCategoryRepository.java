package com.todo.demo.repository;

import com.todo.demo.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
}
