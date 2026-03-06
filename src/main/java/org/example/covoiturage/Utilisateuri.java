package org.example.covoiturage;

import org.example.covoiturage.entities.Utilisateurs;

import java.util.List;
import java.util.Optional;

public interface Utilisateuri {
    List <Utilisateurs> findAll();
    Optional<Utilisateurs> findById(Long id);
    Utilisateurs save(Utilisateurs utilisateurs);
    Utilisateurs update (Utilisateurs utilisateurs);
    void deleteById(Long id);
}
