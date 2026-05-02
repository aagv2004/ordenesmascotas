package com.msduoc.ordenesmascotas.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msduoc.ordenesmascotas.enums.EstadoOrden;
import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.models.OrdenCompra;
import com.msduoc.ordenesmascotas.models.Producto;
import com.msduoc.ordenesmascotas.service.OrdenCompraService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrdenCompraController.class)
public class OrdenCompraControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrdenCompraService service;

    private ObjectMapper mapper;
    private OrdenCompra orden;
    private Cliente cliente;
    private Producto producto;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(200L);
        cliente.setNombre("Alejandro");

        producto = new Producto();
        producto.setId(10L);
        producto.setPrecio(15000);

        orden = new OrdenCompra();
        orden.setId(2L);
        orden.setCliente(cliente);
        orden.setProductos(Arrays.asList(producto));
        orden.setEstado(EstadoOrden.EMITIDA);
        orden.setTotalCompra(15000);

        mapper = new ObjectMapper();
    }

    @Test
    void testGetAllOrdenes() throws Exception {
        when(service.getAllOrdenes()).thenReturn(Arrays.asList(orden));
        mockMvc.perform(get("/ordenes"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(orden))));
    }

    @Test
    void testGetOrdenById() throws Exception {
        when(service.getOrdenById(2L)).thenReturn(Optional.of(orden));
        mockMvc.perform(get("/ordenes/2"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(orden)));
    }

    @Test
    void testCreateOrden() throws Exception {
        when(service.createOrden(any(OrdenCompra.class))).thenReturn(orden);
        mockMvc.perform(post("/ordenes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(orden)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.estado").value("EMITIDA"))
                .andExpect(jsonPath("$.totalCompra").value(15000));
    }

    @Test
    void testUpdateOrden() throws Exception {
        when(service.updateOrden(eq(2L), any(OrdenCompra.class))).thenReturn(orden);
        mockMvc.perform(put("/ordenes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(orden)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(orden)));
    }

    @Test
    void testDeleteOrden() throws Exception {
        mockMvc.perform(delete("/ordenes/2"))
                .andExpect(status().isOk());

        verify(service).deleteOrden(2L);
    }
}
