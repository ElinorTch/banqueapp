package com.projet.banque.services;

import com.projet.banque.entities.Compte;
import com.projet.banque.entities.CompteCourant;
import com.projet.banque.entities.CompteEpargne;
import com.projet.banque.repositories.CompteCourantRepository;
import com.projet.banque.repositories.CompteEpargneRepository;
import com.projet.banque.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompteServices {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private CompteCourantRepository compteCourantRepository;

    @Autowired
    private CompteEpargneRepository compteEpargneRepository;

    public List<Compte> findAllCompte() {
        List<Compte> comptesList = compteRepository.findAll();
        List<Compte> comptes = new ArrayList<>();
        for (int i = 0; i < comptesList.size(); i++) {
            if (comptesList.get(i).getStatus() == 1) {
                comptes.add(comptesList.get(i));
            }
        }
        return comptes;
    }

    public List<CompteCourant> findAllCompteCourant() {
        return compteCourantRepository.findAll();
    }

    public void saveCompte(Compte compte) {
        compteRepository.save(compte);
    }
    public void saveCompteCourant(CompteCourant compte) {
        compteCourantRepository.save(compte);
    }
    public void saveCompteEpargne(CompteEpargne compte) {
        compteEpargneRepository.save(compte);
    }

    public Compte getCompte(Long code) {
        return compteRepository.getReferenceById(code);
    }
    public CompteCourant getCompteCourant(Long code) {
        return compteCourantRepository.getReferenceById(code);
    }
    public CompteEpargne getCompteEpargne(Long code) {
        return compteEpargneRepository.getReferenceById(code);
    }

    public Compte deleteCompte(Long code) {
        Compte compte = compteRepository.getReferenceById(code);
        compte.setStatus(0);
        return compte;
    }

}
