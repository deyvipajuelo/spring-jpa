package com.springjpa.springjpa;

import com.springjpa.springjpa.model.entity.Client;
import com.springjpa.springjpa.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;

@SpringBootApplication
public class SpringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
//        return args -> {
//            Client client1 = new Client(null,"Deyvi","Pajuelo","test@test.com",true,new Timestamp(System.currentTimeMillis()));
//            clientRepository.save(client1);
//            System.out.println(clientRepository.findByIdAndActiveAndEmail(1l,true,"test@test.com"));
//        };
//    }
}
