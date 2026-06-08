package com.pos.demo.controller;

import com.pos.demo.dto.AuthRequest;
import com.pos.demo.model.User;
import com.pos.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for user signup and login")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "Sign up a new user")
    public ResponseEntity<User> signup(@RequestBody AuthRequest request) {
        try {
            User createdUser = userService.signup(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Login an existing user")
    public ResponseEntity<User> login(@RequestBody AuthRequest request) {
        return userService.login(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
