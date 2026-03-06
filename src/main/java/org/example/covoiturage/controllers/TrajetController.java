package org.example.covoiturage.controllers;

import org.example.covoiturage.entities.Trajets;
import org.example.covoiturage.services.TrajetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trajets")
public class TrajetController {

    private final TrajetService trajetService;

    public TrajetController(TrajetService trajetService) {
        this.trajetService = trajetService;
    }

    //  Récupérer tous les trajets
    @GetMapping
    public ResponseEntity<List<Trajets>> getAllTrajets() {
        List<Trajets> trajets = trajetService.findAll();
        return ResponseEntity.ok(trajets);
    }

    //  Récupérer un trajet par ID
    @GetMapping("/{id}")
    public ResponseEntity<Trajets> getTrajetById(@PathVariable Long id) {
        Optional<Trajets> trajet = trajetService.findById(id);
        return trajet.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  Rechercher des trajets par libellé
    @GetMapping("/search")
    public ResponseEntity<List<Trajets>> getTrajetsByLibelle(@RequestParam String libelle) {
        List<Trajets> trajets = trajetService.getByLibelle(libelle);
        if (trajets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trajets);
    }

    //  Créer un nouveau trajet
    @PostMapping
    public ResponseEntity<Trajets> createTrajet(@RequestBody Trajets trajets) {
        Trajets created = trajetService.save(trajets);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //  Mettre à jour un trajet existant
    @PutMapping("/{id}")
    public ResponseEntity<Trajets> updateTrajet(@PathVariable Long id, @RequestBody Trajets trajets) {
        if (!id.equals(trajets.getId())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Trajets updated = trajetService.update(trajets);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //  Supprimer un trajet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrajet(@PathVariable Long id) {
        try {
            trajetService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}