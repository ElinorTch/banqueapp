package com.projet.banque.repositories;

import com.projet.banque.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, String> {
    Optional<Employe> findByMatricule(String matricule);
}
