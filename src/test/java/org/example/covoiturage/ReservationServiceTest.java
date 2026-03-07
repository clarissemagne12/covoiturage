package org.example.covoiturage;

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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        // Cette ligne est essentielle pour que reservationRepository soit injecté
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveReservation() {
        Reservations r1 = new Reservations(1L, "A");

        when(reservationRepository.save(r1)).thenReturn(r1);

        Reservations saved = reservationService.save(r1);

        assertEquals(r1, saved);
        verify(reservationRepository).save(r1);
    }

    @Test
    void testFindAllReservations() {
        Reservations r1 = new Reservations(1L, "A");
        Reservations r2 = new Reservations(2L, "B");

        when(reservationRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Reservations> list = reservationService.findAll();

        assertEquals(2, list.size());
        assertTrue(list.contains(r1));
        assertTrue(list.contains(r2));

        verify(reservationRepository).findAll();
    }

    @Test
    void testDeleteByIdExists() {
        Long id = 1L;

        // Mock : la réservation existe
        when(reservationRepository.existsById(id)).thenReturn(true);
        doNothing().when(reservationRepository).deleteById(id);

        // Appel de la méthode
        reservationService.deleteById(id);

        // Vérification
        verify(reservationRepository).existsById(id);
        verify(reservationRepository).deleteById(id);
    }

    @Test
    void testUpdateReservationNotFound() {
        Reservations r = new Reservations(5L, "Z");

        when(reservationRepository.existsById(5L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reservationService.update(r);
        });

        assertEquals("Reservation non trouvée", exception.getMessage());
        verify(reservationRepository).existsById(5L);
    }

    @Test
    void testUpdateReservationSuccess() {
        Reservations r = new Reservations(5L, "Z");

        when(reservationRepository.existsById(5L)).thenReturn(true);
        when(reservationRepository.save(r)).thenReturn(r);

        Reservations updated = reservationService.update(r);

        assertEquals(r, updated);
        verify(reservationRepository).existsById(5L);
        verify(reservationRepository).save(r);
    }
}