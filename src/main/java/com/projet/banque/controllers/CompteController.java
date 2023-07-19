package com.projet.banque.controllers;

import com.projet.banque.entities.*;
import com.projet.banque.services.ClientServices;
import com.projet.banque.services.CompteServices;
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
public class CompteController {
    @Autowired
    CompteServices compteServices;

    @Autowired
    ClientServices clientServices;

    @GetMapping("compte")
    public String showComptes(Model model) {
        List<Compte> comptes = compteServices.findAllCompte();
        model.addAttribute("compteList", comptes);

//        Ajouter un compte courant
        CompteCourant compteCourant = new CompteCourant();
        model.addAttribute("compteCourant", compteCourant);

//        Ajouter un compte epargne
        CompteEpargne compteEpargne = new CompteEpargne();
        model.addAttribute("compteEpargne", compteEpargne);

//        Ajouter un client au formulaire
        List<Client> client = clientServices.findAllClient();
        model.addAttribute("clients", client);

        return "compte";
    }

//    @GetMapping("addCompteCourant")
//    public String addCompteCourant(Model model) {
//        CompteCourant compte = new CompteCourant();
//        model.addAttribute("compte", compte);
//        return "compteCourantAddView";
//    }

//    @GetMapping("addCompteEpargne")
//    public String addCompteEpargne(Model model) {
//        CompteEpargne compte = new CompteEpargne();
//        model.addAttribute("compte", compte);
//        return "compteEpargneAddView";
//    }

    @PostMapping("saveCompteCourant")
    public String saveCompteCourant(@ModelAttribute("compteCourant") CompteCourant compte){
        compteServices.saveCompteCourant(compte);
        return "redirect:/compte";
    }


    @PostMapping("saveCompteEpargne")
    public String saveCompteEpargne(@ModelAttribute("compteEpargne") CompteEpargne compte){
        compteServices.saveCompteEpargne(compte);
        return "redirect:/compte";
    }

    @GetMapping("/deleteCompte/{numero}")
    public String deleteCompte(@PathVariable(value = "numero") Long code) {
        Compte compte = compteServices.getCompte(code);
        compte.setStatus(0);
        compteServices.saveCompte(compte);
        return "redirect:/compte";
    }

    @GetMapping("/updateCompte/{code}")
    public String updateCompte(@PathVariable(value = "code") Long code, Model model) {
        Compte compte = compteServices.getCompte(code);
        model.addAttribute("compte", compte);
        return "updateCompteView";
    }

}
