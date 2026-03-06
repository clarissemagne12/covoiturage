package org.example.covoiturage;

import org.example.covoiturage.entities.Reservations;

import java.util.List;
import java.util.Optional;

public interface Reservationi {

    List<Reservations> findAll();

    Optional<Reservations> findById(Long id);

    Reservations save(Reservations reservations);

    Reservations update(Reservations reservations);

    void deleteById(Long id);
}