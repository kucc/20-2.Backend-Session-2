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
import org.modelmapper.internal.util.Assert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@ToString(exclude="password")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="email", unique = true, nullable = false, length=320)
    private String email;

    @Setter
    @JsonIgnore
    @Column(name="password", nullable = false, length=1000)
    private String password;

    @Setter
    @Column(name="username",length = 100)
    private String username;

    @Setter
    @Column(name="profile_image")
    @Type(type="text")
    private String profile_image;

    private String auth;

    @Builder
    public User(String email, String password, String username){
        Assert.notNull(email, "email must not be null");
        Assert.notNull(password, "password must not be null");
        Assert.notNull(username, "username must not be null");

        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role:auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
