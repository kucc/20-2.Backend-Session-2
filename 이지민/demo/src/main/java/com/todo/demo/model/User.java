package com.todo.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@ToString(exclude="password")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="email", nullable = false)
    private String email;

    @Setter
    @JsonIgnore
    @Column(name="password", nullable = false)
    private String password;

    @Setter
    @Column(name="username")
    private String username;

    @Setter
    @Column(name="profile_image")
    @Type(type="text")
    private String profile_image;

    @Builder
    public User(String email, String password, String username){
        this.email = email;
        this.password = password;
        this.username = username;
    }

}
