package com.msduoc.ordenesmascotas.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OrdenCompraModelTest {
    @Test
    void testGetterAndSetters() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Pepe");
        cliente.setApellido("Gomez");
        OrdenCompra orden = new OrdenCompra();
        orden.setId(1L);
        orden.setCliente(cliente);

        assertEquals(1L, orden.getId());
        assertEquals("Pepe", orden.getCliente().getNombre());
    }
}
