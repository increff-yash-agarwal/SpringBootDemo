package com.increff.pos.controller;

import com.increff.pos.Helper.DataUser;
import com.increff.pos.Helper.UserForm;
import com.increff.pos.api.UserApi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserApi userApi;

    /**
     * Exposes the entry route for client signup.
     */
    @PostMapping("/signup")
    public DataUser signupClient(@Valid @RequestBody UserForm form) {
        return userApi.createUser(form);
    }
}