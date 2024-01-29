package com.springjpa.springjpa.service;

import com.springjpa.springjpa.model.dto.ClientDto;
import com.springjpa.springjpa.model.entity.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    List<Client> listAll();

    Client findById(Long id);

    Client save(Client client);

    void delete(Client client);

    boolean existsById(Long id);
}
