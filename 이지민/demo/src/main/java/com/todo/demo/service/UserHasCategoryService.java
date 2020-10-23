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

    public void joinUserAndCategory(Category category, User user){
        UserHasCategory userHasCategory = UserHasCategory.builder()
                .category(category)
                .user(user)
                .level(false)
                .build();

        userHasCategory = userHasCategoryRepository.save(userHasCategory);
        user.addCategory(category, userHasCategory);
    }
}
