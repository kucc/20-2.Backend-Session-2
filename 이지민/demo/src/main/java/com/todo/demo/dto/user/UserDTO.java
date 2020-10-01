package com.todo.demo.dto.user;

import com.todo.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDTO {

    @Getter
    public static class Response{
        private Long id;
        private String email;
        private String username;

    }
}
