package com.mitrran.msulearningapi.controller;

import com.mitrran.msulearningapi.model.dto.LoginDto;
import com.mitrran.msulearningapi.model.dto.RegisterDto;
import com.mitrran.msulearningapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto.getEmail());
        return new ResponseEntity<>(authService.userLogin(loginDto), HttpStatus.OK);
    }

    @PutMapping("/logout/{id}")
    public ResponseEntity<Map<String, String>> login(@PathVariable Long id) {
        Map<String, String> map = new HashMap<>();
        authService.logoutUser(id);
        map.put("msg", "User disconnected");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(authService.registerUser(registerDto), HttpStatus.OK);
    }
}
