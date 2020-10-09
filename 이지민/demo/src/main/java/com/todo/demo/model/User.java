package com.todo.demo.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.modelmapper.internal.util.Assert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@ToString(exclude="password")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length=320)
    private String email;

    @Setter
    @JsonIgnore
    @Column(nullable = false, length=1000)
    private String password;

    @Setter
    @Column(length = 100)
    private String username;

    @Setter
    @Column(columnDefinition = "TEXT")
    private String profile_image;

    @Setter
    @OneToMany(mappedBy = "user")
    private Set<UserHasCategory> categories;


    @Builder
    public User(Integer id, String email, String password, String username){
        Assert.notNull(email, "email must not be null");
        Assert.notNull(password, "password must not be null");
        Assert.notNull(username, "username must not be null");

        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
