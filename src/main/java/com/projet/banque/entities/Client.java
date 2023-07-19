package com.projet.banque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;

    private int status = 1;

    private Date dateCreation = new Date();

    private String numero;

    @Column(name = "nom")
    private String nom;

    private Long numeroCni;

    private String prenom;

    @Column(name = "email")
    private String email;

   @OneToMany(mappedBy="client", fetch = FetchType.LAZY)
    private List<Compte> comptes;

}
