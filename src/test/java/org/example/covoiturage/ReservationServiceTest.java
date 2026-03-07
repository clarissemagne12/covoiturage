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

        when(reservationRepository.save(r1)).thenReturn(r1);

        Reservations saved = reservationService.save(r1);

        assertNotNull(saved);
        assertEquals("A", saved.getLibelle());

        verify(reservationRepository).save(r1);
    }

    @Test
    void testFindAllReservations() {

        Reservations r1 = new Reservations(1L, "A");
        Reservations r2 = new Reservations(2L, "B");
        Reservations r3 = new Reservations(3L, "C");

        when(reservationRepository.findAll())
                .thenReturn(Arrays.asList(r1, r2, r3));

        List<Reservations> reservations = reservationService.findAll();

        assertEquals(3, reservations.size());
        verify(reservationRepository).findAll();
    }

    @Test
    void testDeleteByIdExists() {

        when(reservationRepository.existsById(1L)).thenReturn(true);

        reservationService.deleteById(1L);

        verify(reservationRepository).existsById(1L);
        verify(reservationRepository).deleteById(1L);
    }
}