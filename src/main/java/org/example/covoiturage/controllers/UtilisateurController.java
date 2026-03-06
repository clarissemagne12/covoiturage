package org.example.covoiturage.controllers;

import org.example.covoiturage.entities.Utilisateurs;
import org.example.covoiturage.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    //  Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<Utilisateurs>> getAllUtilisateurs() {
        List<Utilisateurs> utilisateurs = utilisateurService.findAll();
        return ResponseEntity.ok(utilisateurs);
    }

    //  Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateurs> getUtilisateurById(@PathVariable Long id) {
        Optional<Utilisateurs> utilisateur = utilisateurService.findById(id);
        return utilisateur.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  Créer un nouvel utilisateur
    @PostMapping
    public ResponseEntity<Utilisateurs> createUtilisateur(@RequestBody Utilisateurs utilisateurs) {
        Utilisateurs created = utilisateurService.save(utilisateurs);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //  Mettre à jour un utilisateur existant
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateurs> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateurs utilisateurs) {
        if (!id.equals(utilisateurs.getId())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Utilisateurs updated = utilisateurService.update(utilisateurs);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        try {
            utilisateurService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Ici, on pourrait attraper les exceptions liées aux FK si l'utilisateur a des réservations/trajets associés
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}