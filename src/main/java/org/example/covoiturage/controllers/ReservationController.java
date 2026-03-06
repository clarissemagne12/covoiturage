package org.example.covoiturage.controllers;

import org.example.covoiturage.entities.Reservations;
import org.example.covoiturage.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Récupérer toutes les réservations
    @GetMapping
    public ResponseEntity<List<Reservations>> getAllReservations() {
        List<Reservations> reservations = reservationService.findAll();
        return ResponseEntity.ok(reservations);
    }

    //  Récupérer une réservation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservations> getReservationById(@PathVariable Long id) {
        Optional<Reservations> reservation = reservationService.findById(id);
        return reservation
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  Créer une nouvelle réservation
    @PostMapping
    public ResponseEntity<Reservations> createReservation(@RequestBody Reservations reservations) {
        Reservations created = reservationService.save(reservations);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Mettre à jour une réservation
    @PutMapping("/{id}")
    public ResponseEntity<Reservations> updateReservation(
            @PathVariable Long id,
            @RequestBody Reservations reservations
    ) {
        if (!id.equals(reservations.getId())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Reservations updated = reservationService.update(reservations);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //  Supprimer une réservation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}