package com.todo.demo.repository;

import com.todo.demo.model.Category;
import com.todo.demo.model.User;
import com.todo.demo.model.UserHasCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasCategoryRepository extends JpaRepository<UserHasCategory, Integer> {
    UserHasCategory findByUserAndCategory(User user, Category category);
}
