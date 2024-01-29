package com.springjpa.springjpa.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ClientDto implements Serializable {

    private Long idClient;
    private String nameClient;
    private String lastNameClient;
    private String emailClient;
    private Boolean active;
}
