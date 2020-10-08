package com.todo.demo.dto.user;

import com.todo.demo.model.User;
import lombok.Getter;
import lombok.Setter;

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
    public static class Response{
        private Long id;
        private String email;
        private String username;

        public Response(){}

        public Response(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
        }


    }
}
