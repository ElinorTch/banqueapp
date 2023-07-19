package com.projet.banque.services;

import com.projet.banque.entities.Client;
import com.projet.banque.entities.Employe;
import com.projet.banque.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientServices {
    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client) {
        client.setDateCreation(new Date());
        clientRepository.save(client);
    }

    public List<Client> findAllClient() {
        List<Client> clientList = clientRepository.findAll();
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getStatus() == 1) {
                clients.add(clientList.get(i));
            }
        }
        return clients;
    }

    public Client getClient(Long code) {
        return clientRepository.getReferenceById(code);
    }
}
