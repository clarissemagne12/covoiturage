package org.example.covoiturage.repositories;

import org.example.covoiturage.entities.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateurs ,Long> {
}
