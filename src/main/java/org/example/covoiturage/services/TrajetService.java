package org.example.covoiturage.services;

import org.example.covoiturage.Trajeti;
import org.example.covoiturage.entities.Trajets;
import org.example.covoiturage.repositories.TrajetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrajetService implements Trajeti {

    private final TrajetRepository trajetRepository;
    private static final Logger logger = LoggerFactory.getLogger(TrajetService.class);

    public TrajetService(TrajetRepository trajetRepository) {
        this.trajetRepository = trajetRepository;
    }

    @Override
    public List<Trajets> findAll() {
        logger.info("Récupération de tous les trajets");
        List<Trajets> trajets = trajetRepository.findAll();
        logger.info("{} trajets trouvés", trajets.size());
        return trajets;
    }

    @Override
    public Optional<Trajets> findById(Long id) {
        logger.info("Recherche du trajet avec l'id {}", id);
        Optional<Trajets> trajet = trajetRepository.findById(id);
        if (trajet.isPresent()) {
            logger.info("Trajet trouvé : {}", trajet.get());
        } else {
            logger.warn("Aucun trajet trouvé avec l'id {}", id);
        }
        return trajet;
    }

    @Override
    public List<Trajets> getByLibelle(String libelle) {
        logger.info("Recherche des trajets avec le libellé '{}'", libelle);
        List<Trajets> trajets = trajetRepository.getByLibelle(libelle);
        if (trajets.isEmpty()) {
            logger.warn("Aucun trajet trouvé avec le libellé '{}'", libelle);
        } else {
            logger.info("{} trajets trouvés avec le libellé '{}'", trajets.size(), libelle);
        }
        return trajets;
    }

    @Override
    public Trajets save(Trajets trajets) {
        logger.info("Création d'un nouveau trajet : {}", trajets);
        Trajets savedTrajet = trajetRepository.save(trajets);
        logger.info("Trajet créé avec succès : {}", savedTrajet);
        return savedTrajet;
    }

    @Override
    public Trajets update(Trajets trajets) {
        logger.info("Mise à jour du trajet : {}", trajets);
        if (trajets.getId() == null || !trajetRepository.existsById(trajets.getId())) {
            logger.error("Impossible de mettre à jour le trajet. ID introuvable : {}", trajets.getId());
            throw new IllegalArgumentException("Trajet non trouvé pour mise à jour");
        }
        Trajets updatedTrajet = trajetRepository.save(trajets);
        logger.info("Trajet mis à jour avec succès : {}", updatedTrajet);
        return updatedTrajet;
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Suppression du trajet avec l'id {}", id);
        if (!trajetRepository.existsById(id)) {
            logger.warn("Trajet avec l'id {} introuvable. Suppression annulée.", id);
            return;
        }
        trajetRepository.deleteById(id);
        logger.info("Trajet avec l'id {} supprimé avec succès", id);
    }
}