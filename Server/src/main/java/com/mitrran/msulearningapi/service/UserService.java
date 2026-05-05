package com.mitrran.msulearningapi.service;

import com.mitrran.msulearningapi.model.User;
import com.mitrran.msulearningapi.model.dto.UserDto;
import com.mitrran.msulearningapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> getAllUsers() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userRepository.findAll().stream().map(this::mapUserDto).toList());
        return map;
    }

    public Map<String, Object> getUserById(Long id) {
        Map<String, Object> map = new HashMap<>();
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> map.put("data", mapUserDto(value)));
        map.put("status", "success");
        return map;
    }

    public void updateUser(UserDto userDto) {
        Optional<User> user = userRepository.findById(userDto.getId());
        User user1;
        if (user.isPresent()) {
            user1 = user.get();

            user1.setDepartment(userDto.getDepartment());
            user1.setFullName(userDto.getFullName());
            user1.setPhoneNumber(userDto.getPhoneNumber());
            user1.setEmail(userDto.getEmail());
            user1.setProfileImage(userDto.getProfileImage());

            userRepository.save(user1);
        }
    }

    public UserDto mapUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setDepartment(user.getDepartment());
        userDto.setProfileImage(user.getProfileImage());
        userDto.setConnected(user.getConnected());
        return userDto;
    }
}
