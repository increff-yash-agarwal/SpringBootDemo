package com.increff.pos.Helper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

// Input Request Contract Model
@Getter
@Setter
public class UserForm {

    @NotBlank(message = "Email address cannot be empty or blank")
    @Email(message = "Please provide a valid structured email address")
    private String email;
}