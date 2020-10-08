package com.todo.demo.service;

import com.todo.demo.dto.user.UserDto;
import com.todo.demo.exception.CustomUserNotFoundException;
import com.todo.demo.model.User;
import com.todo.demo.repository.UserRepository;
import com.todo.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public User createUser(UserDto.Request requestDto){
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = requestDto.toEntity();
        userRepository.save(user);
        return user;
    }

    public Boolean emailDoesNotExist(String email){
        return userRepository.findByEmail(email) == null;
    }

    public Boolean passwordMatch(String password, String email){
        User user = userRepository.findByEmail(email);
        return passwordEncoder.matches(password, user.getPassword());
    }

    public String createTokenById(String email){
        User user = userRepository.findByEmail(email);
        return jwtTokenProvider.createToken(String.valueOf(user.getId()));
    }

    @Override
    public User loadUserByUsername(String userPK) throws UsernameNotFoundException {
        return userRepository.findById(Long.valueOf(userPK))
                .orElseThrow(CustomUserNotFoundException::new);

    }
}
