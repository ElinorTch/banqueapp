package com.projet.banque.repositories;

import com.projet.banque.entities.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetraitRepository extends JpaRepository<Retrait, Long> {
}
