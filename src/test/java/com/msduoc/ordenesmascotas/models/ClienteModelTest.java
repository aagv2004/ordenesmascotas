package com.msduoc.ordenesmascotas.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ClienteModelTest {
    @Test
    void testGetterAndSetters() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Pepe");
        cliente.setApellido("Gomez");

        assertEquals(1L, cliente.getId());
        assertEquals("Pepe", cliente.getNombre());
        assertEquals("Gomez", cliente.getApellido());
    }
}
