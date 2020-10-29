package com.todo.demo.dto;

import com.todo.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    @Getter
    @Setter
    public static class Login{
        private String email;
        private String password;
    }

    @Getter
    @Setter
    public static class Request{
        private String email;
        private String password;
        private String confirmedPassword;
        private String username;

        public User toEntity(){
            return User.builder()
                    .email(email)
                    .password(password)
                    .username(username)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{
        private Integer id;
        private String email;
        private String username;

        public static Response of(User user) {
            int id = user.getId();
            String email = user.getEmail();
            String username = user.getUsername();

            return new Response(id, email, username);
        }

        public static List<Response> of(List<User> userList){
            return userList.stream()
                    .map(Response::of)
                    .collect(Collectors.toList());
        }


    }
}
