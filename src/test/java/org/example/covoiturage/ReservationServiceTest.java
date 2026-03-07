package org.example.covoiturage;

import org.example.covoiturage.entities.Reservations;
import org.example.covoiturage.repositories.ReservationRepository;
import org.example.covoiturage.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void testFindAll() {
        List<Reservations> reservations = new ArrayList<>();
        reservations.add(new Reservations(1L, "A"));
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservations> result = reservationService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(reservationRepository).findAll();
    }

    @Test
    void testFindByIdFound() {
        Reservations reservation = new Reservations(1L, "A");
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        Optional<Reservations> result = reservationService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(reservationRepository).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(reservationRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Reservations> result = reservationService.findById(2L);

        assertTrue(result.isEmpty());
        verify(reservationRepository).findById(2L);
    }

    @Test
    void testSave() {
        Reservations reservation = new Reservations(1L, "A");
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservations result = reservationService.save(reservation);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(reservationRepository).save(reservation);
    }

    @Test
    void testUpdateSuccess() {
        Reservations reservation = new Reservations(1L, "A");
        when(reservationRepository.existsById(1L)).thenReturn(true);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservations result = reservationService.update(reservation);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(reservationRepository).existsById(1L);
        verify(reservationRepository).save(reservation);
    }

    @Test
    void testUpdateNotFound() {
        Reservations reservation = new Reservations(2L, "B");
        when(reservationRepository.existsById(2L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                reservationService.update(reservation)
        );

        assertEquals("Reservation non trouvée", exception.getMessage());
        verify(reservationRepository).existsById(2L);
        verify(reservationRepository, never()).save(any());
    }

    @Test
    void testDeleteByIdExists() {
        when(reservationRepository.existsById(1L)).thenReturn(true);
        doNothing().when(reservationRepository).deleteById(1L);

        reservationService.deleteById(1L);

        verify(reservationRepository).existsById(1L);
        verify(reservationRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotExists() {
        when(reservationRepository.existsById(2L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reservationService.deleteById(2L);
        });

        assertEquals("Reservation non trouvée", exception.getMessage());
        verify(reservationRepository).existsById(2L);
        verify(reservationRepository, never()).deleteById(anyLong());
    }
}