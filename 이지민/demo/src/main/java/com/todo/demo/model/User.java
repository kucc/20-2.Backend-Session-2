package com.todo.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;

    @Column(name="email")
    String email;

    @Column(name="password")
    String password;

    @Column(name="username")
    String username;

    @Column(name="profile_image")
    @Type(type="text")
    String profile_image;

    @Builder
    public User(int id, String email, String password, String username, String profile_image){
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.profile_image = profile_image;
    }

}
