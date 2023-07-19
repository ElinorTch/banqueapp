package com.projet.banque.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @GetMapping(value={"/", "/accueil", "/logout"})
    public String showAccueil() {
        return "accueil";
    }
}
