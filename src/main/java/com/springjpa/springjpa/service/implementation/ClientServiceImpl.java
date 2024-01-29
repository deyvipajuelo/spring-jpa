package com.springjpa.springjpa.service.implementation;

import com.springjpa.springjpa.model.dao.ClientDao;
import com.springjpa.springjpa.model.dto.ClientDto;
import com.springjpa.springjpa.model.entity.Client;
import com.springjpa.springjpa.model.payload.MessageResponse;
import com.springjpa.springjpa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public boolean existsById(Long id) {
        return clientDao.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Client> listAll() {
        return (List<Client>) clientDao.findAll();
    }

    public ResponseEntity<?> findAllClients() {
        List<Client> clients = this.listAll();

        if (clients.isEmpty()) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("No existen registros!")
                            .body(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("Consulta exitosa!")
                        .body(clients)
                        .build()
                , HttpStatus.OK);
    }

    public ResponseEntity<?> findClientById(Long id) {
        Client client = this.findById(id);

        if (client == null) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("El cliente no existe!")
                            .body(null)
                            .build()
                    , HttpStatus.NOT_FOUND);
        }

        ClientDto clientDto = ClientDto.builder()
                .idClient(client.getId())
                .nameClient(client.getName())
                .lastNameClient(client.getLast_name())
                .emailClient(client.getEmail())
                .active(client.getActive())
                .build();

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("Consulta exitosa!")
                        .body(clientDto)
                        .build()
                , HttpStatus.OK);
    }

    public ResponseEntity<?> saveClient(ClientDto clientDto) {
        try {
            Client client = Client.builder()
                    .name(clientDto.getNameClient())
                    .last_name(clientDto.getLastNameClient())
                    .email(clientDto.getEmailClient())
                    .active(clientDto.getActive())
                    .build();
            Client clientSaved = this.save(client);
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

    public ResponseEntity<?> updateClient(ClientDto clientDto) {
        try {

            if ( ! this.existsById(clientDto.getIdClient())) {
                return new ResponseEntity<>(
                        MessageResponse.builder()
                                .message("El cliente no existe!!")
                                .body(null)
                                .build()
                        , HttpStatus.NOT_FOUND);
            }

            Client client = Client.builder()
                    .id(clientDto.getIdClient())
                    .name(clientDto.getNameClient())
                    .last_name(clientDto.getLastNameClient())
                    .email(clientDto.getEmailClient())
                    .active(clientDto.getActive())
                    .build();

            this.save(client);

            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("Cliente actualizado correctamente!")
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

    public ResponseEntity<?> deleteClientById(Long id) {
        try {
            Client clientDelete = clientDao.findById(id).orElse(null);
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
                            .message("Error al eliminar el cliente")
                            .body(null)
                            .build(), HttpStatus.ACCEPTED
            );
        }
    }
}
