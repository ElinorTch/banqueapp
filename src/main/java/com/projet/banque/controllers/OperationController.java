package com.projet.banque.controllers;

import com.projet.banque.configuration.CustomUserEmployeDetailsService;
import com.projet.banque.configuration.EmployeDetails;
import com.projet.banque.entities.*;
import com.projet.banque.services.CompteServices;
import com.projet.banque.services.EmployeServices;
import com.projet.banque.services.OperationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class OperationController {
    @Autowired
    OperationServices operationServices;

    @Autowired
    CompteServices compteServices;

    @Autowired
    EmployeServices employeServices;

    private EmployeDetails employeDetails;


    @GetMapping("/operations")
    public String showOperation(Model model) {
        List<Operation> operations = operationServices.findAllOperation();
        model.addAttribute("operationList", operations);

        Retrait retrait = new Retrait();
        model.addAttribute("retrait", retrait);

        Depot depot = new Depot();
        model.addAttribute("depot", depot);

        List<Compte> compte = compteServices.findAllCompte();
        model.addAttribute("compte", compte);

        return "operation";
    }

    @PostMapping("/saveRetrait")
    public String saveRetrait(@ModelAttribute("retrait") Retrait retrait, Model model) {
        CompteCourant compte = compteServices.getCompteCourant(retrait.getCompte().getNumero());
        Double sommeCompte = compte.getSolde() * compte.getDecouvert() + compte.getSolde();
        if ( sommeCompte >= retrait.getMontant()) {
            compte.setSolde(compte.getSolde() - retrait.getMontant());
            compteServices.saveCompteCourant(compte);
            operationServices.saveRetrait(retrait);
            return "redirect:/operations";
        }
        model.addAttribute("error", "Erreur dans la transaction");
        return "redirect:/operations";
    }

    @PostMapping("/saveDepot")
    public String saveDepot(@ModelAttribute("depot") Depot depot, Model model) {
        Compte compte = compteServices.getCompte(depot.getCompte().getNumero());
        Double sommeCompte = compte.getSolde();
        compte.setSolde(sommeCompte + depot.getMontant());
        compteServices.saveCompte(compte);
        operationServices.saveDepot(depot);
        return "redirect:/operations";
    }

    @GetMapping("/virement")
    public String showVirement(Model model) {
        List<Transfert> operations = operationServices.findAllTransfert();
        model.addAttribute("transferts", operations);

        List<Compte> compte = compteServices.findAllCompte();
        model.addAttribute("compte", compte);

        Transfert operation = new Transfert();
        model.addAttribute("transfert", operation);

        return "virement";
    }

    @PostMapping("/saveTransfert")
    public String saveTransfert(@ModelAttribute("transfert") Transfert transfert) {
        Compte compte = compteServices.getCompte(transfert.getCompte().getNumero());
        Compte destinataire = compteServices.getCompte(transfert.getDestinataire().getNumero());
        if (compte.getSolde() >= transfert.getMontant()) {
            compte.setSolde(compte.getSolde() - transfert.getMontant());
            destinataire.setSolde(destinataire.getSolde() + transfert.getMontant());
            transfert.setDateOperation(new Date());
            compteServices.saveCompte(compte);
            compteServices.saveCompte(destinataire);
            operationServices.saveTransfert(transfert);
            return "redirect:/virement";
        }
        return "redirect:/virement";
    }

}
