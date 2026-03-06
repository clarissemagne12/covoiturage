package org.example.covoiturage;

import org.example.covoiturage.entities.Reservations;
import org.example.covoiturage.repositories.ReservationRepository;
import org.example.covoiturage.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void testFindAllReservations() {
        when(reservationRepository.findAll()).thenReturn(List.of(new Reservations(), new Reservations()));

        List<Reservations> reservations = reservationService.findAll();

        assertEquals(2, reservations.size());
        verify(reservationRepository).findAll();
    }

    @Test
    void testFindByIdFound() {
        Reservations res = new Reservations();
        res.setId(1L); // Long, non null
        res.setLibelle("Trajet Test");

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(res));

        Optional<Reservations> result = reservationService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(reservationRepository).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(reservationRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Reservations> result = reservationService.findById(2L);

        assertFalse(result.isPresent());
        verify(reservationRepository).findById(2L);
    }

    @Test
    void testSaveReservation() {
        Reservations res = new Reservations();
        res.setId(3L);
        res.setLibelle("Trajet A");

        when(reservationRepository.save(res)).thenReturn(res);

        Reservations saved = reservationService.save(res);

        assertNotNull(saved);
        assertEquals(3L, saved.getId());
        assertEquals("Trajet A", saved.getLibelle());
        verify(reservationRepository).save(res);
    }

    @Test
    void testUpdateReservationSuccess() {
        Reservations res = new Reservations();
        res.setId(4L);

        when(reservationRepository.existsById(4L)).thenReturn(true);
        when(reservationRepository.save(res)).thenReturn(res);

        Reservations updated = reservationService.update(res);

        assertNotNull(updated);
        assertEquals(4L, updated.getId());
        verify(reservationRepository).existsById(4L);
        verify(reservationRepository).save(res);
    }

    @Test
    void testUpdateReservationNotFound() {
        Reservations res = new Reservations();
        res.setId(5L);

        when(reservationRepository.existsById(5L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reservationService.update(res));

        assertEquals("Réservation introuvable", exception.getMessage());
        verify(reservationRepository).existsById(5L);
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void testDeleteReservationSuccess() {
        when(reservationRepository.existsById(6L)).thenReturn(true);
        doNothing().when(reservationRepository).deleteById(6L);

        reservationService.deleteById(6L);

        verify(reservationRepository).existsById(6L);
        verify(reservationRepository).deleteById(6L);
    }

    @Test
    void testDeleteReservationNotFound() {
        when(reservationRepository.existsById(7L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> reservationService.deleteById(7L));

        assertEquals("Réservation introuvable", exception.getMessage());
        verify(reservationRepository).existsById(7L);
        verify(reservationRepository, never()).deleteById(anyLong());
    }
}