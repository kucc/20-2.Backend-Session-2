package com.todo.demo.controller;

import com.todo.demo.dto.CategoryDto;
import com.todo.demo.model.Category;
import com.todo.demo.model.User;
import com.todo.demo.service.CategoryService;
import com.todo.demo.service.UserHasCategoryService;
import com.todo.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserHasCategoryService userHasCategoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserHasCategoryService userHasCategoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userHasCategoryService = userHasCategoryService;
        this.userService = userService;
    }


    @PostMapping(path="")
    public ResponseEntity<CategoryDto.Response> createCategory(@RequestBody CategoryDto.Request requestDto, @AuthenticationPrincipal User user){
        Category category = categoryService.createCategory(requestDto);
        userHasCategoryService.joinUserAndCategory(category, user);
        return ResponseEntity.ok(new CategoryDto.Response(category));
    }

    @GetMapping(path="/{id}")
    public void findCategoryById(@PathVariable int id, @AuthenticationPrincipal User user){
        Category category = categoryService.findCategoryById(id);

    }

    @GetMapping(path="/")
    public ResponseEntity<CategoryDto.ListResponse> getCategoriesOfUser(@AuthenticationPrincipal User user){
        int userId = user.getId();
        List<Category> categories = categoryService.getCategories(userId);

        return ResponseEntity.ok(new CategoryDto.ListResponse(categories));
    }

    @GetMapping(path="/{id}/users")
    public void findUsersInCategory(@PathVariable int id){
        Category category = categoryService.findCategoryById(id);

    }
}
