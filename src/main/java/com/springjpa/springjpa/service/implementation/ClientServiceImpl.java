package com.springjpa.springjpa.service.implementation;

import com.springjpa.springjpa.model.dao.ClientDao;
import com.springjpa.springjpa.model.dto.ClientDto;
import com.springjpa.springjpa.model.entity.Client;
import com.springjpa.springjpa.model.payload.MessageResponse;
import com.springjpa.springjpa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;
//    public ClientServiceImplementation(ClientDao clientDao) {
//        this.clientDao = clientDao;
//    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> listAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public ResponseEntity<?> save(ClientDto clientDto) {
        try {
            Client client = Client.builder()
                    .id(clientDto.getIdClient())
                    .name(clientDto.getNameClient())
                    .last_name(clientDto.getLastNameClient())
                    .email(clientDto.getEmailClient())
                    .active(clientDto.getActive())
                    .build();
            Client clientSaved = clientDao.save(client);
            clientDto.setIdClient(clientSaved.getId());

            return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("Cliente guardado correctamente!")
                        .body(clientDto)
                        .build(), HttpStatus.CREATED
            );

        } catch (Exception ex) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message(ex.getMessage())
                            .body(null)
                            .build(), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteById(Long id) {

        try {
            Client clientDelete = this.findById(id);
            this.delete(clientDelete);
            ClientDto clientDto = ClientDto.builder()
                    .idClient(clientDelete.getId())
                    .nameClient(clientDelete.getName())
                    .lastNameClient(clientDelete.getLast_name())
                    .emailClient(clientDelete.getEmail())
                    .active(clientDelete.getActive())
                    .build();

            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Cliente eliminado correctamente!!")
                            .body(clientDto)
                            .build()
                    , HttpStatus.ACCEPTED
            );
        } catch (Exception ex) {
            log.error("Error al eliminar el cliente con ID {}", id, ex);
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("eRRORRRRRROOOO")
                            .body(null)
                            .build(), HttpStatus.ACCEPTED
            );
        }
    }
}
