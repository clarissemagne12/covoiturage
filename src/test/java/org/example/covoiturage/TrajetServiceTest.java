package org.example.covoiturage;

import org.example.covoiturage.entities.Trajets;
import org.example.covoiturage.repositories.TrajetRepository;
import org.example.covoiturage.services.TrajetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TrajetServiceTest {

    @Mock
    private TrajetRepository trajetRepository;

    @InjectMocks
    private TrajetService trajetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Trajets> trajets = new ArrayList<>();
        trajets.add(new Trajets());
        when(trajetRepository.findAll()).thenReturn(trajets);

        List<Trajets> result = trajetService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(trajetRepository).findAll();
    }

    @Test
    void testFindByIdFound() {
        Trajets trajet = new Trajets();
        trajet.setId(1L);

        when(trajetRepository.findById(1L)).thenReturn(Optional.of(trajet));

        Optional<Trajets> result = trajetService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(trajetRepository).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(trajetRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Trajets> result = trajetService.findById(2L);

        assertTrue(result.isEmpty());
        verify(trajetRepository).findById(2L);
    }

    @Test
    void testGetByLibelleFound() {
        Trajets trajet = new Trajets();
        trajet.setLibelle("Paris-Douala");
        List<Trajets> trajets = List.of(trajet);

        when(trajetRepository.getByLibelle("Paris-Douala")).thenReturn(trajets);

        List<Trajets> result = trajetService.getByLibelle("Paris-Douala");

        assertEquals(1, result.size());
        assertEquals("Paris-Douala", result.get(0).getLibelle());
        verify(trajetRepository).getByLibelle("Paris-Douala");
    }

    @Test
    void testGetByLibelleNotFound() {
        when(trajetRepository.getByLibelle("Inexistant")).thenReturn(List.of());

        List<Trajets> result = trajetService.getByLibelle("Inexistant");

        assertTrue(result.isEmpty());
        verify(trajetRepository).getByLibelle("Inexistant");
    }

    @Test
    void testSave() {
        Trajets trajet = new Trajets();
        trajet.setLibelle("Paris-Douala");

        when(trajetRepository.save(trajet)).thenReturn(trajet);

        Trajets result = trajetService.save(trajet);

        assertNotNull(result);
        assertEquals("Paris-Douala", result.getLibelle());
        verify(trajetRepository).save(trajet);
    }

    @Test
    void testUpdateSuccess() {
        Trajets trajet = new Trajets();
        trajet.setId(1L);
        trajet.setLibelle("Paris-Douala");

        when(trajetRepository.existsById(1L)).thenReturn(true);
        when(trajetRepository.save(trajet)).thenReturn(trajet);

        Trajets result = trajetService.update(trajet);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(trajetRepository).existsById(1L);
        verify(trajetRepository).save(trajet);
    }

    @Test
    void testUpdateNotFound() {
        Trajets trajet = new Trajets();
        trajet.setId(2L);

        when(trajetRepository.existsById(2L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                trajetService.update(trajet)
        );

        assertEquals("Trajet non trouvé pour mise à jour", exception.getMessage());
        verify(trajetRepository).existsById(2L);
        verify(trajetRepository, never()).save(any());
    }

    @Test
    void testDeleteByIdExists() {
        when(trajetRepository.existsById(1L)).thenReturn(true);

        trajetService.deleteById(1L);

        verify(trajetRepository).existsById(1L);
        verify(trajetRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotExists() {
        when(trajetRepository.existsById(2L)).thenReturn(false);

        trajetService.deleteById(2L);

        verify(trajetRepository).existsById(2L);
        verify(trajetRepository, never()).deleteById(anyLong());
    }
}