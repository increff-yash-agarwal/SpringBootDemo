package com.increff.pos.dto;

import com.increff.pos.db.ClientPojo;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClientDto {

    /**
     * Business Rule: Verifies that a client name is uniquely registered.
     */
    public void validateUniqueClient(Optional<ClientPojo> existingClient) {
        if (existingClient.isPresent()) {
            throw new IllegalArgumentException("Client with this name already exists.");
        }
    }

    /**
     * Business Rule: Verifies name conflicts during modifications.
     * Prevents hijacking an existing client's identity.
     */
    public void validateUpdateClient(Optional<ClientPojo> existingClient, Integer currentId) {
        if (existingClient.isPresent() && !existingClient.get().getId().equals(currentId)) {
            throw new IllegalArgumentException("Another client with this name already exists.");
        }
    }
}