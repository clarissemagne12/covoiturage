
package org.example.covoiturage.entities;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
@Entity
@Table(catalog = "covoiturage", schema = "")
@NamedQueries({
        @NamedQuery(name = "Utilisateurs.findAll", query = "SELECT u FROM Utilisateurs u"),
        @NamedQuery(name = "Utilisateurs.findById", query = "SELECT u FROM Utilisateurs u WHERE u.id = :id"),
        @NamedQuery(name = "Utilisateurs.findByNom", query = "SELECT u FROM Utilisateurs u WHERE u.nom = :nom"),
        @NamedQuery(name = "Utilisateurs.findByPrenom", query = "SELECT u FROM Utilisateurs u WHERE u.prenom = :prenom"),
        @NamedQuery(name = "Utilisateurs.findByMotPasse", query = "SELECT u FROM Utilisateurs u WHERE u.motPasse = :motPasse"),
        @NamedQuery(name = "Utilisateurs.findByTelephone", query = "SELECT u FROM Utilisateurs u WHERE u.telephone = :telephone"),
        @NamedQuery(name = "Utilisateurs.findByDateCreation", query = "SELECT u FROM Utilisateurs u WHERE u.dateCreation = :dateCreation"),
        @NamedQuery(name = "Utilisateurs.findByType", query = "SELECT u FROM Utilisateurs u WHERE u.type = :type")})
public class Utilisateurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(nullable = false, length = 191)
    private String nom;
    @Basic(optional = false)
    @Column(nullable = false, length = 191)
    private String prenom;
    @Basic(optional = false)
    @Column(nullable = false, length = 191)
    private String motPasse;
    @Basic(optional = false)
    @Column(nullable = false)
    private int telephone;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Basic(optional = false)
    @Column(nullable = false, length = 191)
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUtilisateur")
    private Collection<Reservations> reservationsCollection;

    public Utilisateurs() {
    }

    public Utilisateurs(Long id) {
        this.id = id;
    }

    public Utilisateurs(Long id, String nom, String prenom, String motPasse, int telephone, Date dateCreation, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.motPasse = motPasse;
        this.telephone = telephone;
        this.dateCreation = dateCreation;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Reservations> getReservationsCollection() {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<Reservations> reservationsCollection) {
        this.reservationsCollection = reservationsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateurs)) {
            return false;
        }
        Utilisateurs other = (Utilisateurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject3.Utilisateurs[ id=" + id + " ]";
    }

}
