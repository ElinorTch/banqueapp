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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte",discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private Date dateCreation = new Date();
    private double solde;
    private int status = 1;

    @Column(insertable=false, updatable=false)
    private String type_compte;

    @ManyToOne
    @JoinColumn(name ="code_client")
    private Client client;

    @OneToMany(mappedBy="compte")
    private List<Operation> operations;

    @OneToMany(mappedBy = "destinataire")
    private List<Transfert> transferts;

}
