package com.increff.pos.Helper;

import lombok.Getter;
import lombok.Setter;

// Output Response Contract Model
@Getter
@Setter
public class DataUser { // Follows your strict 'Data' suffix naming rule

    private Integer id;
    private String email;
    private String role;
}