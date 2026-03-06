package org.example.covoiturage;

import org.example.covoiturage.entities.Trajets;
import java.util.List;
import java.util.Optional;

public interface Trajeti {

    // Récupérer tous les trajets
    List<Trajets> findAll();

    // Récupérer un trajet par son ID
    Optional<Trajets> findById(Long id);

    // Récupérer les trajets correspondant à un libellé
    List<Trajets> getByLibelle(String libelle);

    // Créer un nouveau trajet
    Trajets save(Trajets trajets);

    // Mettre à jour un trajet existant
    Trajets update(Trajets trajets);

    // Supprimer un trajet par son ID
    void deleteById(Long id);
}