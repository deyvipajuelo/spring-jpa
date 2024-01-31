package com.springjpa.springjpa.controller;

import com.springjpa.springjpa.model.dto.ClientDto;
import com.springjpa.springjpa.model.entity.Client;
import com.springjpa.springjpa.service.implementation.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("clients")
    public ResponseEntity<?> clients() {
        return clientService.findAllClients();
    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> client(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return clientService.deleteClientById(id);
    }

    @PostMapping("client")
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto) {
        return clientService.saveClient(clientDto);
    }

    @PutMapping("client/{id}")
    public ResponseEntity<?> update(@RequestBody ClientDto client, @PathVariable Long id) {
        return clientService.updateClient(client, id);
    }

    @GetMapping("searchByEmail/{email}")
    public ResponseEntity<?> findClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }

}
