package com.projet.banque.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Employe {

    @Id
    private String matricule;
    private String password;

    @Email
    private String email;

    private String nom;
    private String prenom;
    private int status = 1;

    private String role;

    @OneToMany(mappedBy = "employe")
    List<Operation> operations;

}
