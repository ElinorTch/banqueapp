package com.projet.banque.controllers;

import com.projet.banque.configuration.CustomUserEmployeDetailsService;
import com.projet.banque.entities.Client;
import com.projet.banque.entities.Employe;
import com.projet.banque.services.ClientServices;
import com.projet.banque.services.CompteServices;
import com.projet.banque.services.EmployeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientServices clientServices;

    @Autowired
    private CompteServices compteServices;

    @GetMapping("clients")
    public String showClient(Model model) {
        List<Client> clients = clientServices.findAllClient();
        model.addAttribute("clientList", clients);// La liste de mes clients

        Client client = new Client();
        model.addAttribute("client", client);// Pour l'ajout des Clients

        return "client";
    }

//    @GetMapping("addClients")
//    public String addClient(Model model) {
//        Client client = new Client();
//        model.addAttribute("client", client);
//        return "client";
//    }

    @GetMapping("/updateClient/{code}")
    public String updateClient(@PathVariable(value = "code") Long code, Model model) {
        Client client = clientServices.getClient(code);
        model.addAttribute("client", client);
        return "clientUpdate";
    }

    @GetMapping("/deleteClient/{code}")
    public String deleteEmployee(@PathVariable(value = "code") Long code, Model model) {
        Client client = clientServices.getClient(code);
        client.setStatus(0);
        for (int i = 0; i < client.getComptes().size(); i++){
            client.getComptes().get(i).setStatus(0);
            compteServices.saveCompte(client.getComptes().get(i));
        }
        clientServices.saveClient(client);
        return "redirect:/clients";
    }

    @PostMapping("/saveClient")
    public String saveMedecin(@ModelAttribute("client") Client client) {
        clientServices.saveClient(client);
        return "redirect:/clients";
    }
}
