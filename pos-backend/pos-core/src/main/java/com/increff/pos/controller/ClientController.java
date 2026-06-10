package com.increff.pos.controller;

import com.increff.pos.Helper.ClientData;
import com.increff.pos.Helper.ClientForm;
import com.increff.pos.api.ClientApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client Master", description = "Operations to manage corporate clients and vendors")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientApi clientApi;

    /**
     * Endpoint to register a brand new client profile.
     */
    @Operation(summary = "Create a new client")
    @PostMapping
    public ClientData createClient(@Valid @RequestBody ClientForm form) {
        return clientApi.createClient(form);
    }

    /**
     * Endpoint to modify an existing client profile details.
     */
    @Operation(summary = "Update an existing client's details")
    @PutMapping("/{id}")
    public ClientData updateClient(@PathVariable Integer id, @Valid @RequestBody ClientForm form) {
        return clientApi.updateClient(id, form);
    }

    /**
     * Endpoint to fetch a single client's profile record by unique ID.
     */
    @Operation(summary = "Get a single client by ID")
    @GetMapping("/{id}")
    public ClientData getClient(@PathVariable Integer id) {
        return clientApi.getClient(id);
    }

    /**
     * Endpoint to aggregate all currently registered clients.
     */
    @Operation(summary = "Get a list of all registered clients")
    @GetMapping
    public List<ClientData> getAllClients() {
        return clientApi.getAllClients();
    }
}