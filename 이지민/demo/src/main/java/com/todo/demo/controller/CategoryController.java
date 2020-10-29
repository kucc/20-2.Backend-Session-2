package com.todo.demo.controller;

import com.todo.demo.dto.CategoryDto;
import com.todo.demo.dto.UserDto;
import com.todo.demo.model.Category;
import com.todo.demo.model.User;
import com.todo.demo.service.CategoryService;
import com.todo.demo.service.UserHasCategoryService;
import com.todo.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;
    private final UserHasCategoryService userHasCategoryService;

    public CategoryController(CategoryService categoryService, UserService userService, UserHasCategoryService userHasCategoryService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.userHasCategoryService = userHasCategoryService;
    }


    @PostMapping(path="")
    public ResponseEntity<CategoryDto.Response> createCategory(@RequestBody CategoryDto.Request requestDto, @AuthenticationPrincipal User user){
        Category category = categoryService.createCategory(requestDto);
        userHasCategoryService.joinUserAndCategory(category, user);
        return ResponseEntity.ok(CategoryDto.Response.of(category));
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<CategoryDto.Response> findCategoryById(@PathVariable int id, @AuthenticationPrincipal User user) throws Exception {
        Category category = categoryService.findCategoryById(id);

        if(!userService.isJoined(category, user)) throw new Exception("권한이 없습니다.");
        return ResponseEntity.ok(CategoryDto.Response.of(category));
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable int id, @AuthenticationPrincipal User user) throws Exception{
        Category category = categoryService.findCategoryById(id);
        if(!userHasCategoryService.isOwner(category, user)) {
            throw new Exception("권한이 없습니다.");
        }

        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path="")
    public ResponseEntity<List<CategoryDto.Response>> getCategoriesOfUser(@AuthenticationPrincipal User user){
        int userId = user.getId();
        List<Category> categories = categoryService.getCategories(userId);
        return ResponseEntity.ok(CategoryDto.Response.of(categories));
    }

    @PostMapping(path="/{id}/user")
    public ResponseEntity<Object> addUserToCategory(@AuthenticationPrincipal User user, @PathVariable int id){
        Category category = categoryService.findCategoryById(id);
        userHasCategoryService.addUserToCategory(category, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path="/{id}/users")
    public ResponseEntity<List<UserDto.Response>> findUsersInCategory(@PathVariable int id){
        List<User> userList = userService.getUsersByCategoryId(id);
        return ResponseEntity.ok().body(UserDto.Response.of(userList));
    }
}
