package org.example.covoiturage.repositories;

import org.example.covoiturage.entities.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository <Reservations , Long> {
}
