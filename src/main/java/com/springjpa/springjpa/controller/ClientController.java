package com.springjpa.springjpa.controller;

import com.springjpa.springjpa.model.dto.ClientDto;
import com.springjpa.springjpa.model.entity.Client;
import com.springjpa.springjpa.model.payload.MessageResponse;
import com.springjpa.springjpa.service.implementation.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("clients")
    public List<Client> clients() {
        return clientService.listAll();
    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> client(@PathVariable Long id) {
        Map<String, String> errors = new HashMap<>();
        try {
            Client client = clientService.findById(id);
            if (client == null) throw new Exception("El cliente no existe");
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (Exception ext) {
            errors.put("Mensaje", ext.getMessage());
            errors.put("Client", null);
            return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return clientService.deleteById(id);
    }

    @PostMapping("client")
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto) {
        return clientService.save(clientDto);
    }

    @PutMapping("client")
    public ResponseEntity<?> update(@RequestBody ClientDto client) {
        return clientService.save(client);
    }
}
