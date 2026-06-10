package com.increff.pos.Helper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientForm {

    @NotBlank(message = "Client name cannot be empty or blank")
    @Size(min = 2, max = 100, message = "Client name must be between 2 and 100 characters")
    private String name;
}