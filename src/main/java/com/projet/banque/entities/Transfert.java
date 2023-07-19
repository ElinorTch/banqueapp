package com.projet.banque.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("transfert")
public class Transfert extends Operation{

    @ManyToOne
    @JoinColumn(name = "destinataire")
    private Compte destinataire;
}
