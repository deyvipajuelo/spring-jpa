package com.springjpa.springjpa.model.dao;

import com.springjpa.springjpa.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client, Long> {
}
