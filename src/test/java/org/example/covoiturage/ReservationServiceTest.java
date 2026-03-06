package org.example.covoiturage;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.covoiturage.entities.Reservations;
import org.example.covoiturage.repositories.ReservationRepository;
import org.example.covoiturage.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveReservation() {
        Reservations r1 = new Reservations(1L, "A");
        Reservations r2 = new Reservations(2L, "B");
        Reservations r3 = new Reservations(3L, "C");

        when(reservationRepository.save(r1)).thenReturn(r1);
        when(reservationRepository.save(r2)).thenReturn(r2);
        when(reservationRepository.save(r3)).thenReturn(r3);
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(r1, r2, r3));

        reservationService.save(r1);
        reservationService.save(r2);
        reservationService.save(r3);

        List<Reservations> reservations = reservationService.findAll();
        assertEquals(3, reservations.size(), "La taille de la liste doit être 3");
    }
//
//    @Test
//    void testUpdateReservationNotFound() {
//        Reservations r = new Reservations(5L, "Z");
//        when(reservationRepository.existsById(5L)).thenReturn(false);
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            reservationService.update(r);
//        });
//
//        assertEquals("Reservation non trouvée", exception.getMessage());
//        verify(reservationRepository).existsById(5L);
//    }

    @Test
    void testDeleteByIdExists() {
        when(reservationRepository.existsById(1L)).thenReturn(true);

        reservationService.deleteById(1L);

        verify(reservationRepository).existsById(1L);
        verify(reservationRepository).deleteById(1L);
    }
}