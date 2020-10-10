package com.todo.demo.controller;

import com.todo.demo.dto.CategoryDto;
import com.todo.demo.dto.UserDto;
import com.todo.demo.model.Category;
import com.todo.demo.model.User;
import com.todo.demo.model.UserHasCategory;
import com.todo.demo.service.CategoryService;
import com.todo.demo.service.UserHasCategoryService;
import com.todo.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final UserHasCategoryService userHasCategoryService;

    public UserController(UserService userService, CategoryService categoryService, UserHasCategoryService userHasCategoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.userHasCategoryService = userHasCategoryService;
    }

    @GetMapping(path="")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<UserDto.Response> getOneUser(@PathVariable int id){
        User user = userService.findOne(id);

        return ResponseEntity.ok().body(new UserDto.Response(user));
    }

    @GetMapping(path="/me")
    public void getUserCategory(@AuthenticationPrincipal User user){

        user.getCategories();
    }

    @PostMapping(path="/category")
    public void createCategory(@RequestBody CategoryDto.Request requestDto, @AuthenticationPrincipal User user){
        Category category = categoryService.createCategory(requestDto);
        UserHasCategory userHasCategory = userHasCategoryService.makeUserHasCategory(category, user);

        category = categoryService.addUser(category, userHasCategory);
        user = userService.addCategory(user, userHasCategory);
    }


}
