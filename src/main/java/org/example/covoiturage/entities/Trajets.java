
package org.example.covoiturage.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author user
 */
@Entity
@Table(catalog = "covoiturage", schema = "")
@NamedQueries({
        @NamedQuery(name = "Trajets.findAll", query = "SELECT t FROM Trajets t"),
        @NamedQuery(name = "Trajets.findById", query = "SELECT t FROM Trajets t WHERE t.id = :id"),
        @NamedQuery(name = "Trajets.findByCodeTrajet", query = "SELECT t FROM Trajets t WHERE t.codeTrajet = :codeTrajet"),
        @NamedQuery(name = "Trajets.findByLibelle", query = "SELECT t FROM Trajets t WHERE t.libelle = :libelle"),
        @NamedQuery(name = "Trajets.findByStockage", query = "SELECT t FROM Trajets t WHERE t.stockage = :stockage"),
        @NamedQuery(name = "Trajets.findByPointDepart", query = "SELECT t FROM Trajets t WHERE t.pointDepart = :pointDepart"),
        @NamedQuery(name = "Trajets.findByPointArrive", query = "SELECT t FROM Trajets t WHERE t.pointArrive = :pointArrive"),
        @NamedQuery(name = "Trajets.findByType", query = "SELECT t FROM Trajets t WHERE t.type = :type")})
public class Trajets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "code_trajet", nullable = false, length = 191)
    private String codeTrajet;
    @Basic(optional = false)
    @Column(nullable = false, length = 191)
    private String libelle;
    @Basic(optional = false)
    @Column(nullable = false)
    private long stockage;
    @Basic(optional = false)
    @Column(nullable = false, length = 191)
    private String pointDepart;
    @Basic(optional = false)
    @Column(nullable = false, length = 191)
    private String pointArrive;
    @Basic(optional = false)
    @Column(nullable = false)
    private int type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTrajet")
    private Collection<Reservations> reservationsCollection;

    public Trajets() {
    }

    public Trajets(Long id) {
        this.id = id;
    }

    public Trajets(Long id, String codeTrajet, String libelle, long stockage, String pointDepart, String pointArrive, int type) {
        this.id = id;
        this.codeTrajet = codeTrajet;
        this.libelle = libelle;
        this.stockage = stockage;
        this.pointDepart = pointDepart;
        this.pointArrive = pointArrive;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTrajet() {
        return codeTrajet;
    }

    public void setCodeTrajet(String codeTrajet) {
        this.codeTrajet = codeTrajet;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public long getStockage() {
        return stockage;
    }

    public void setStockage(long stockage) {
        this.stockage = stockage;
    }

    public String getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(String pointDepart) {
        this.pointDepart = pointDepart;
    }

    public String getPointArrive() {
        return pointArrive;
    }

    public void setPointArrive(String pointArrive) {
        this.pointArrive = pointArrive;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
        if (!(object instanceof Trajets)) {
            return false;
        }
        Trajets other = (Trajets) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject3.Trajets[ id=" + id + " ]";
    }

}
