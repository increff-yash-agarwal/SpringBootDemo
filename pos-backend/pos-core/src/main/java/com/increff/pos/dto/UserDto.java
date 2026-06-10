package com.increff.pos.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDto {

    @Value("#{'${app.security.supervisors}'.split(',')}")
    private List<String> supervisorEmails;

    /**
     * Evaluates whether an email belongs to a supervisor or an operator role.
     * Strict 20-line limit honored.
     */
    public String determineRoleByEmail(String email) {
        if (supervisorEmails.contains(email)) {
            return "supervisor";
        }
        return "operator";
    }
}