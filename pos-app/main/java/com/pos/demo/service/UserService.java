package com.pos.demo.service;

import com.pos.demo.dto.AuthRequest;
import com.pos.demo.dto.UserDto;
import com.pos.demo.model.Role;
import com.pos.demo.model.User;
import com.pos.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Value("${pos.supervisors:}")
    private String supervisorsString;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(determineRole(userDto.getEmail()));
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Integer id, UserDto userDto) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            // optionally update role? Typically role isn't updated by user
            return userRepository.save(user);
        });
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User signup(AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(determineRole(request.getEmail()));
        return userRepository.save(user);
    }

    public Optional<User> login(AuthRequest request) {
        return userRepository.findByEmail(request.getEmail());
    }

    private Role determineRole(String email) {
        if (email == null) return Role.OPERATOR;
        List<String> supervisors = Arrays.asList(supervisorsString.split(","));
        boolean isSupervisor = supervisors.stream().anyMatch(s -> s.trim().equalsIgnoreCase(email.trim()));
        return isSupervisor ? Role.SUPERVISOR : Role.OPERATOR;
    }
}
