package org.example.covoiturage.repositories;

import org.example.covoiturage.entities.Trajets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrajetRepository extends JpaRepository<Trajets ,Long> {


    List<Trajets> getByLibelle(String libelle);
}
