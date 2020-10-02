package com.todo.demo.service;

import com.todo.demo.dto.user.UserDto;
import com.todo.demo.model.User;
import com.todo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDto.Request requestDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        requestDto.setPassword(encoder.encode(requestDto.getPassword()));
        User user = User.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .username(requestDto.getUsername()).build();
       userRepository.save(user);
       return user;
    }

    public Boolean emailCheck(String email){
        return userRepository.findByEmail(email) == null;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException((username)));
    }
}
