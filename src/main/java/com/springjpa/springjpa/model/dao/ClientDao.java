package com.springjpa.springjpa.model.dao;

import com.springjpa.springjpa.model.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client, Long> {
    @Query("SELECT c FROM Client c where c.email = :email")
    public Client getClientByEmail(String email);
}
