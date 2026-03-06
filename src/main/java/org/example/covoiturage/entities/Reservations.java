package org.example.covoiturage.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter

@Table(catalog = "covoiturage", schema = "")
@NamedQueries({
        @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r"),
        @NamedQuery(name = "Reservations.findByIdReservation", query = "SELECT r FROM Reservations r WHERE r.idReservation = :idReservation"),
        @NamedQuery(name = "Reservations.findByStatut", query = "SELECT r FROM Reservations r WHERE r.statut = :statut"),
        @NamedQuery(name = "Reservations.findByDateReservation", query = "SELECT r FROM Reservations r WHERE r.dateReservation = :dateReservation"),
        @NamedQuery(name = "Reservations.findByNumeroPlace", query = "SELECT r FROM Reservations r WHERE r.numeroPlace = :numeroPlace")})
public class Reservations implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long idReservation;
    @Basic(optional = false)
    @Column(nullable = false, length = 191)
    private String statut;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateReservation;
    @Basic(optional = false)
    @Column(name = "numero_place", nullable = false)
    private int numeroPlace;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Utilisateurs idUtilisateur;
    @JoinColumn(name = "idTrajet", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Trajets idTrajet;
    

    public Reservations() {
    }

    public Reservations(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Reservations(Long idReservation, String statut, Date dateReservation, int numeroPlace) {
        this.idReservation = idReservation;
        this.statut = statut;
        this.dateReservation = dateReservation;
        this.numeroPlace = numeroPlace;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getNumeroPlace() {
        return numeroPlace;
    }

    public void setNumeroPlace(int numeroPlace) {
        this.numeroPlace = numeroPlace;
    }

    public Utilisateurs getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateurs idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Trajets getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Trajets idTrajet) {
        this.idTrajet = idTrajet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReservation != null ? idReservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.idReservation == null && other.idReservation != null) || (this.idReservation != null && !this.idReservation.equals(other.idReservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject3.Reservations[ idReservation=" + idReservation + " ]";
    }
    private String libelle;
    public String getLibelle() {
        return libelle;
    }

    public Long getId() {
        return idReservation;
    }

    public void setId(Long id) {
        this.idReservation = 1l;  // ✅ assigner la valeur passée
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
