package com.todo.demo.service;

import com.todo.demo.model.Category;
import com.todo.demo.model.User;
import com.todo.demo.model.UserHasCategory;
import com.todo.demo.repository.UserHasCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserHasCategoryService {

    private final UserHasCategoryRepository userHasCategoryRepository;

    public UserHasCategoryService(UserHasCategoryRepository userHasCategoryRepository) {
        this.userHasCategoryRepository = userHasCategoryRepository;
    }

    public UserHasCategory makeUserHasCategory(Category category, User user){
        UserHasCategory userHasCategory = UserHasCategory.builder()
                .category(category)
                .user(user)
                .level(false)
                .build();
        return userHasCategoryRepository.save(userHasCategory);
    }
}
