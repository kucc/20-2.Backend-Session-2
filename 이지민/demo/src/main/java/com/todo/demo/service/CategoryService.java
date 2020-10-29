package com.todo.demo.service;

import com.todo.demo.dto.CategoryDto;
import com.todo.demo.model.Category;

import com.todo.demo.repository.CategoryRepository;
import com.todo.demo.repository.CategoryRepositorySupport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryRepositorySupport categoryRepositorySupport;

    public CategoryService(CategoryRepository categoryRepository, CategoryRepositorySupport categoryRepositorySupport) {
        this.categoryRepository = categoryRepository;
        this.categoryRepositorySupport = categoryRepositorySupport;
    }

    public Category createCategory(CategoryDto.Request requestDto){
        Category category = requestDto.toEntity();
        return categoryRepository.save(category);
    }


    public Category findCategoryById(int id){
        return categoryRepository.getOne(id);
    }

    public List<Category> getCategories(int userId){
        return categoryRepositorySupport.findCategoriesByUserId(userId);
    }

    public void deleteCategoryById(int id){
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
    }



}
