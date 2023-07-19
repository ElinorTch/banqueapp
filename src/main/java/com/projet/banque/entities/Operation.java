package com.projet.banque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_operation", discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private Date dateOperation = new Date();

    private String libelle;

    private double montant;

    @Column(insertable=false, updatable=false)
    private String type_operation;

    @ManyToOne
    @JoinColumn(name = "code_compte")
    private Compte compte;

    @ManyToOne
    @JoinColumn(name = "operation_employe")
    private Employe employe;
}
