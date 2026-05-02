package com.msduoc.ordenesmascotas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.models.Producto;
import com.msduoc.ordenesmascotas.repository.ClienteRepository;
import com.msduoc.ordenesmascotas.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {
    
    @Mock
    private ProductoRepository repository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ProductoServiceImpl service;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(3L);
        producto.setNombreProducto("producto1");
        producto.setPrecio(1500);
    }

    @Test
    void testGetAllProductos() {
        List<Producto> expected = Arrays.asList(producto);
        List<Cliente> expClientes = Arrays.asList(new Cliente());

        when(clienteRepository.findAll()).thenReturn(expClientes);
        when(repository.findAll()).thenReturn(expected);

        assertEquals(expected, service.getAllProductos());
    }

    @Test
    void testGetProductoById() {
        when(clienteRepository.findById(3L)).thenReturn(Optional.of(new Cliente()));
        
        when(repository.findById(3L)).thenReturn(Optional.of(producto));
        assertEquals(Optional.of(producto), service.getProductoById(3L));
    }

    @Test
    void testCreateProducto() {
        when(repository.save(producto)).thenReturn(producto);
        assertEquals(producto, service.createProducto(producto));
    }

    @Test
    void testUpdateProductoExists() {
        when(repository.existsById(3L)).thenReturn(true);
        when(repository.save(producto)).thenReturn(producto);
        Producto result = service.updateProducto(3L, producto);
        assertEquals(3L, producto.getId());
        assertEquals(producto, result);
        verify(repository).save(producto);
    }

    @Test
    void testUpdateProductoNotExists() {
        when(repository.existsById(3L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> service.updateProducto(3L, producto));
        verify(repository, never()).save(any());
    }

    @Test
    void testDeleteProducto() {
        when(clienteRepository.findById(3L)).thenReturn(Optional.of(new Cliente()));

        service.deleteProducto(3L);
        verify(repository).deleteById(3L);
    }
}
