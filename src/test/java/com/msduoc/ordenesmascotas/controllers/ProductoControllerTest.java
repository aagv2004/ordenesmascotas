package com.msduoc.ordenesmascotas.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;
import com.msduoc.ordenesmascotas.controllers.api.ProductoController;
import com.msduoc.ordenesmascotas.models.Producto;
import com.msduoc.ordenesmascotas.service.ProductoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService service;

    private ObjectMapper mapper;
    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(3L);
        producto.setNombreProducto("producto1");
        producto.setPrecio(15000);

        mapper = new ObjectMapper();
    }

    @Test
    void testGetAllProductos() throws Exception {
        when(service.getAllProductos()).thenReturn(Arrays.asList(producto));
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(producto))));
    }

    @Test
    void testGetProductoById() throws Exception {
        when(service.getProductoById(3L)).thenReturn(Optional.of(producto));
        mockMvc.perform(get("/api/productos/3"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(producto)));
    }

    @Test
    void testCreateProducto() throws Exception {
        when(service.createProducto(any(Producto.class))).thenReturn(producto);
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(producto)));
    }

    @Test
    void testUpdateProducto() throws Exception {
        when(service.updateProducto(eq(3L), any(Producto.class))).thenReturn(producto);
        mockMvc.perform(put("/api/productos/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(producto)));
    }

    @Test
    void testDeleteProducto() throws Exception {
        mockMvc.perform(delete("/api/productos/3"))
                .andExpect(status().isOk());
        verify(service).deleteProducto(3L);
    }
}
