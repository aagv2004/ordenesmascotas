package com.msduoc.ordenesmascotas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.msduoc.ordenesmascotas.enums.EstadoOrden;
import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.models.OrdenCompra;
import com.msduoc.ordenesmascotas.models.Producto;
import com.msduoc.ordenesmascotas.repository.ClienteRepository;
import com.msduoc.ordenesmascotas.repository.OrdenCompraRepository;
import com.msduoc.ordenesmascotas.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class OrdenCompraImplTest {

    @Mock
    private OrdenCompraRepository ordenCompraRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private OrdenCompraServiceImpl service;

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
    }

    @Test
    void testGetAllOrdenes() {
        List<OrdenCompra> expected = Arrays.asList(orden);
        when(ordenCompraRepository.findAll()).thenReturn(expected);
        assertEquals(expected, service.getAllOrdenes());
    }

    @Test
    void testGetOrdenById() {
        when(ordenCompraRepository.findById(2L)).thenReturn(Optional.of(orden));
        assertEquals(Optional.of(orden), service.getOrdenById(2L));
    }

    @Test
    void testCreateOrden() {
        when(clienteRepository.findById(200L)).thenReturn(Optional.of(cliente));
        when(productoRepository.findById(10L)).thenReturn(Optional.of(producto));
        when(ordenCompraRepository.save(any(OrdenCompra.class))).thenReturn(orden);

        OrdenCompra result = service.createOrden(orden);

        assertNotNull(result.getFechaCreacion());
        assertEquals(EstadoOrden.EMITIDA, result.getEstado());
        assertEquals(cliente, result.getCliente());
        assertEquals(15000, result.getTotalCompra());
        verify(ordenCompraRepository).save(any(OrdenCompra.class));
    }

    @Test
    void testUpdateOrdenExists() {
        OrdenCompra existente = new OrdenCompra();
        existente.setId(2L);
        existente.setFechaCreacion(LocalDate.of(2026, 1, 1));
        existente.setCliente(cliente);
        existente.setEstado(EstadoOrden.EMITIDA);
        existente.setProductos(Arrays.asList(producto));
        existente.setTotalCompra(15000);

        when(ordenCompraRepository.existsById(2L)).thenReturn(true);
        when(ordenCompraRepository.findById(2L)).thenReturn(Optional.of(existente));
        when(productoRepository.findById(10L)).thenReturn(Optional.of(producto));
        when(ordenCompraRepository.save(any(OrdenCompra.class))).thenReturn(orden);

        OrdenCompra result = service.updateOrden(2L, orden);

        assertEquals(2L, result.getId());
        assertEquals(existente.getFechaCreacion(), result.getFechaCreacion());
        assertEquals(EstadoOrden.EMITIDA, result.getEstado());
        assertEquals(15000, result.getTotalCompra());
        verify(ordenCompraRepository).save(any(OrdenCompra.class));
    }

    @Test
    void testUpdateOrdenNotExists() {
        when(ordenCompraRepository.existsById(2L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> service.updateOrden(2L, orden));
        verify(ordenCompraRepository, never()).save(any());
    }

    @Test
    void testDeleteOrden() {
        service.deleteOrden(2L);
        verify(ordenCompraRepository).deleteById(2L);
    }
}