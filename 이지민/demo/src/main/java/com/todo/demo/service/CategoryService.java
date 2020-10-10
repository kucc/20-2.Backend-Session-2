package com.todo.demo.service;

import com.todo.demo.dto.CategoryDto;
import com.todo.demo.model.Category;
import com.todo.demo.model.UserHasCategory;
import com.todo.demo.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryDto.Request requestDto){
        Category category = requestDto.toEntity();
        return category;
    }

    public Category addUser(Category category, UserHasCategory user){
        category.addUser(user);
        return category;
    }
}
