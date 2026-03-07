package org.example.covoiturage.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "reservations", catalog = "covoiturage")
public class Reservations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private String statut;

    @Temporal(TemporalType.DATE)
    private Date dateReservation;

    private int numeroPlace;

    private String libelle;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateurs idUtilisateur;

    @ManyToOne
    @JoinColumn(name = "idTrajet")
    private Trajets idTrajet;

    // Constructeur vide requis par JPA
    public Reservations() {
    }

    // Constructeur pratique pour les tests
    public Reservations(Long id, String libelle) {
        this.idReservation = id;
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getId() {
        return idReservation;
    }

    public void setId(Long id) {
        this.idReservation = id;
    }
}