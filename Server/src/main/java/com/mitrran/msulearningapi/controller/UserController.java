package com.mitrran.msulearningapi.controller;

import com.mitrran.msulearningapi.model.dto.UserDto;
import com.mitrran.msulearningapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// @CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> updateUserInfo(@RequestBody UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        userService.updateUser(userDto);
        map.put("status", "success");
        map.put("msg", "User info updated successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
