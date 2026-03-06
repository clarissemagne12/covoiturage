package org.example.covoiturage.services;

import org.example.covoiturage.Reservationi;
import org.example.covoiturage.entities.Reservations;
import org.example.covoiturage.entities.Trajets;
import org.example.covoiturage.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements Reservationi {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservations> findAll() {
        logger.info("Récupération de toutes les réservations");
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservations> findById(Long id) {
        logger.info("Recherche de la réservation avec l'id : {}", id);
        return reservationRepository.findById(id);
    }

    @Override
    public Reservations save(Reservations reservations) {
        logger.info("Création d'une réservation pour le trajet : {}",
                reservations.getLibelle());
        return reservationRepository.save(reservations);
    }

//    @Override
//    public Reservations update(Reservations reservations) {
//        logger.info("Mise à jour de la réservation avec l'id : {}", reservations.getId());
//
//        if (!reservationRepository.existsById(reservations.getId())) {
//            logger.error("Réservation avec id {} introuvable", reservations.getId());
//            throw new RuntimeException("Réservation introuvable");
//        }
//
//        return reservationRepository.save(reservations);
//    }

    @Override
    public Reservations update(Reservations reservations) {
        if (!reservationRepository.existsById(reservations.getId())) {
            throw new RuntimeException("Reservation non trouvée"); // ← même texte que le test
        }
        return reservationRepository.save(reservations);
    }

    @Override
    public void deleteById(Long id) {
        logger.warn("Suppression de la réservation avec l'id : {}", id);

        if (!reservationRepository.existsById(id)) {
            logger.error("Impossible de supprimer. Réservation avec id {} introuvable", id);
            throw new RuntimeException("Réservation introuvable");
        }

        reservationRepository.deleteById(id);
    }
}