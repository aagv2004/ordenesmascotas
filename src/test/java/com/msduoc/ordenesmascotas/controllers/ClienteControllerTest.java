package com.msduoc.ordenesmascotas.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;
import com.msduoc.ordenesmascotas.controllers.api.ClienteController;
import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService service;

    private ObjectMapper mapper;
    private Cliente cliente;
    
    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Alejandro");

        mapper = new ObjectMapper();
    }

    @Test
    void testGetAllClientes() throws Exception {
        when(service.getAllClientes()).thenReturn(Arrays.asList(cliente));
        mockMvc.perform(get("/api/clientes"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(cliente))));
    }

    @Test
    void testGetClienteById() throws Exception {
        when(service.getClienteById(1L)).thenReturn(Optional.of(cliente));
        mockMvc.perform(get("/api/clientes/1"))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(cliente)));
    }

    @Test
    void testCreateCliente() throws Exception {
        when(service.createCliente(any(Cliente.class))).thenReturn(cliente);
        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(cliente)));
    }

    @Test
    void testUpdateCliente() throws Exception {
        when(service.updateCliente(eq(1L), any(Cliente.class))).thenReturn(cliente);
        mockMvc.perform(put("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(cliente)));
    }

    @Test
    void testDeleteCliente() throws Exception {
        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isOk());
        verify(service).deleteCliente(1L);
    }
}
