package com.mitrran.msulearningapi.service;

import com.mitrran.msulearningapi.model.User;
import com.mitrran.msulearningapi.model.dto.LoginDto;
import com.mitrran.msulearningapi.model.dto.RegisterDto;
import com.mitrran.msulearningapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;


    public Map<String, String> userLogin(LoginDto loginDto) {
        Map<String, String> map = new HashMap<>();
        Optional<User> user;
        user = userRepository.findByEmailIgnoreCase(loginDto.getEmail());
        if (user.isPresent()) {
            if (Objects.equals(user.get().getPassword(), loginDto.getPassword())) {
                map.put("status", "success");
                map.put("userId", String.valueOf(user.get().getId()));
                map.put("name", user.get().getFullName());
                map.put("msg", "Authenticated");
                user.get().setConnected(true);
                userRepository.save(user.get());
            } else {
                map.put("status", "failed");
                map.put("msg", "Invalid Password");
            }
        } else {
            map.put("status", "failed");
            map.put("msg", "Invalid Email");
        }
        return map;
    }

    public Map<String, String> registerUser(RegisterDto registerDto) {
        Map<String, String> map = new HashMap<>();
        Optional<User> user;
        user = userRepository.findByEmailIgnoreCase(registerDto.getEmail());
        if (user.isPresent()) {
            map.put("status", "failed");
            map.put("msg", "User Already registered");
        } else {
            User newUser = new User();
            if (Objects.equals(registerDto.getEmail(), "")) {
                map.put("status", "failed");
                map.put("msg", "Email field should not be empty");
                return map;
            }
            if (Objects.equals(registerDto.getPassword(), "")) {
                map.put("status", "failed");
                map.put("msg", "Password field should not be empty");
                return map;
            }
            if (Objects.equals(registerDto.getFullName(), "")) {
                map.put("status", "failed");
                map.put("msg", "Full Name field should not be empty");
                return map;
            }
            newUser.setEmail(registerDto.getEmail());
            newUser.setPassword(registerDto.getPassword());
            newUser.setFullName(registerDto.getFullName());
            userRepository.save(newUser);
            map.put("status", "success");
            map.put("msg", "user registered successfully");
        }
        return map;
    }

    public void logoutUser(Long userId) {
        Optional<User> user;
        user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setConnected(false);
            userRepository.save(user.get());
        }
    }
}
