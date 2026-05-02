package com.msduoc.ordenesmascotas.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ProductoModelTest {
    @Test
    void testGetterAndSetters() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombreProducto("producto1");

        assertEquals(1L, producto.getId());
        assertEquals("producto1", producto.getNombreProducto());
    }
}
