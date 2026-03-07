package org.example.covoiturage.services;

import org.example.covoiturage.Utilisateuri;
import org.example.covoiturage.entities.Utilisateurs;
import org.example.covoiturage.repositories.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService implements Utilisateuri {

    private final UtilisateurRepository utilisateurRepository;
    private  final Logger logger = LoggerFactory.getLogger(UtilisateurService.class);

    public UtilisateurService (UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<Utilisateurs> findAll() {
        logger.info("Récupération de tous les utilisateurs");
        List<Utilisateurs> utilisateurs = utilisateurRepository.findAll();
        logger.info("{} utilisateurs trouvés", utilisateurs.size());
        return utilisateurs;
    }

    @Override
    public Optional<Utilisateurs> findById(Long id) {
        logger.info("Recherche de l'utilisateur avec l'id {}", id);
        Optional<Utilisateurs> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isPresent()) {
            logger.info("Utilisateur trouvé : {}", utilisateur.get());
        } else {
            logger.warn("Aucun utilisateur trouvé avec l'id {}", id);
        }
        return utilisateur;
    }

    @Override
    public Utilisateurs save(Utilisateurs utilisateurs) {
        logger.info("Création d'un nouvel utilisateur : {}", utilisateurs);
        Utilisateurs savedUser = utilisateurRepository.save(utilisateurs);
        logger.info("Utilisateur créé avec succès : {}", savedUser);
        return savedUser;
    }

    @Override
    public Utilisateurs update(Utilisateurs utilisateurs) {
        logger.info("Mise à jour de l'utilisateur : {}", utilisateurs);
        if (utilisateurs.getId() == null || !utilisateurRepository.existsById(utilisateurs.getId())) {
            logger.error("Impossible de mettre à jour l'utilisateur. ID introuvable : {}", utilisateurs.getId());
            throw new IllegalArgumentException("Utilisateur non trouvé pour mise à jour");
        }
        Utilisateurs updatedUser = utilisateurRepository.save(utilisateurs);
        logger.info("Utilisateur mis à jour avec succès : {}", updatedUser);
        return updatedUser;
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Suppression de l'utilisateur avec l'id {}", id);
        if (!utilisateurRepository.existsById(id)) {
            logger.warn("Utilisateur avec l'id {} introuvable. Suppression annulée.", id);
            return;
        }
        utilisateurRepository.deleteById(id);
        logger.info("Utilisateur avec l'id {} supprimé avec succès", id);
    }
}