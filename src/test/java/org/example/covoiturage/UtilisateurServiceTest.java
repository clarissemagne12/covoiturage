package org.example.covoiturage;

import org.example.covoiturage.entities.Utilisateurs;
import org.example.covoiturage.repositories.UtilisateurRepository;
import org.example.covoiturage.services.UtilisateurService;
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

class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Utilisateurs> users = new ArrayList<>();
        users.add(new Utilisateurs());
        when(utilisateurRepository.findAll()).thenReturn(users);

        List<Utilisateurs> result = utilisateurService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(utilisateurRepository).findAll();
    }

    @Test
    void testFindByIdFound() {
        Utilisateurs user = new Utilisateurs();
        user.setId(1L);

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<Utilisateurs> result = utilisateurService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(utilisateurRepository).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(utilisateurRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Utilisateurs> result = utilisateurService.findById(2L);

        assertTrue(result.isEmpty());
        verify(utilisateurRepository).findById(2L);
    }

    @Test
    void testSave() {
        Utilisateurs user = new Utilisateurs();
        user.setId(1L);

        when(utilisateurRepository.save(user)).thenReturn(user);

        Utilisateurs result = utilisateurService.save(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(utilisateurRepository).save(user);
    }

    @Test
    void testUpdateSuccess() {
        Utilisateurs user = new Utilisateurs();
        user.setId(1L);

        when(utilisateurRepository.existsById(1L)).thenReturn(true);
        when(utilisateurRepository.save(user)).thenReturn(user);

        Utilisateurs result = utilisateurService.update(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(utilisateurRepository).existsById(1L);
        verify(utilisateurRepository).save(user);
    }

    @Test
    void testUpdateNotFound() {
        Utilisateurs user = new Utilisateurs();
        user.setId(2L);

        when(utilisateurRepository.existsById(2L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                utilisateurService.update(user)
        );

        assertEquals("Utilisateur non trouvé pour mise à jour", exception.getMessage());
        verify(utilisateurRepository).existsById(2L);
        verify(utilisateurRepository, never()).save(any());
    }

    @Test
    void testDeleteByIdExists() {
        when(utilisateurRepository.existsById(1L)).thenReturn(true);

        utilisateurService.deleteById(1L);

        verify(utilisateurRepository).existsById(1L);
        verify(utilisateurRepository).deleteById(1L);
    }

    @Test
    void testDeleteByIdNotExists() {
        when(utilisateurRepository.existsById(2L)).thenReturn(false);

        utilisateurService.deleteById(2L);

        verify(utilisateurRepository).existsById(2L);
        verify(utilisateurRepository, never()).deleteById(anyLong());
    }
}