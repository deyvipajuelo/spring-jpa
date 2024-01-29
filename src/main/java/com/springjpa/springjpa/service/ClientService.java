package com.springjpa.springjpa.service;

import com.springjpa.springjpa.model.dto.ClientDto;
import com.springjpa.springjpa.model.entity.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    List<Client> listAll();

    Client findById(Long id);

    ResponseEntity save(ClientDto client);

    void delete(Client client);

    ResponseEntity deleteById(Long id);
}
