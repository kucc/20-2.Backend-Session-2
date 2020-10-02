package com.todo.demo.dto.user;

import com.todo.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter
    @Setter
    public static class Request{
        private String email;
        private String password;
        private String username;
    }

    @Getter
    @Setter
    public static class Response{
        private Long id;
        private String email;
        private String username;

    }
}
