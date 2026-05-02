package com.msduoc.ordenesmascotas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.repository.ClienteRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {
    
    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteServiceImpl service;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Pedrito");
    }

    @Test
    void testGetAllClientes() {
        List<Cliente> expected = Arrays.asList(cliente);
        when(repository.findAll()).thenReturn(expected);
        assertEquals(expected, service.getAllClientes());
    }

    @Test
    void testGetClienteById() {
        when(repository.findById(1L)).thenReturn(Optional.of(cliente));
        assertEquals(Optional.of(cliente), service.getClienteById(1L));
    }

    @Test
    void testCreateCliente() {
        when(repository.save(cliente)).thenReturn(cliente);
        assertEquals(cliente, service.createCliente(cliente));
    }

    @Test
    void testUpdateClienteExists() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(cliente)).thenReturn(cliente);
        Cliente result = service.updateCliente(1L, cliente);
        assertEquals(1L, cliente.getId());
        assertEquals(cliente, result);
        verify(repository).save(cliente);
    }

    @Test
    void testUpdateClienteNotExists() {
        when(repository.existsById(1L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> service.updateCliente(1L, cliente));
        verify(repository, never()).save(any());
    }

    @Test
    void testDeleteCliente() {
        service.deleteCliente(1L);
        verify(repository).deleteById(1L);
    }
}
