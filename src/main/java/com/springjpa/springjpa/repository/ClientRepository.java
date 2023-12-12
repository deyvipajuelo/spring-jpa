package com.springjpa.springjpa.repository;

import com.springjpa.springjpa.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    public Client findByIdAndActiveAndEmail(Long id, Boolean active, String email);
}
