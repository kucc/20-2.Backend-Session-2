package com.todo.demo.service;

import com.todo.demo.exception.CustomUserNotFoundException;
import com.todo.demo.model.User;
import com.todo.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String userPK) throws UsernameNotFoundException {
        return userRepository.findById(Long.valueOf(userPK))
                .orElseThrow(CustomUserNotFoundException::new);

    }
}
