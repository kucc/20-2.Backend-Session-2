package com.todo.demo.repository;

import com.todo.demo.model.UserHasCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasCategoryRepository extends JpaRepository<UserHasCategory, Integer> {
}
