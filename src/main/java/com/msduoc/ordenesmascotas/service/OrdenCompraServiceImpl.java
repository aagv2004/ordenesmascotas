package com.msduoc.ordenesmascotas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msduoc.ordenesmascotas.enums.EstadoOrden;
import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.models.OrdenCompra;
import com.msduoc.ordenesmascotas.models.Producto;
import com.msduoc.ordenesmascotas.repository.ClienteRepository;
import com.msduoc.ordenesmascotas.repository.OrdenCompraRepository;
import com.msduoc.ordenesmascotas.repository.ProductoRepository;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<OrdenCompra> getAllOrdenes() {
        return ordenCompraRepository.findAll();
    }

    @Override
    public Optional<OrdenCompra> getOrdenById(Long id) {
        return ordenCompraRepository.findById(id);
    }

    @Override
    public OrdenCompra createOrden(OrdenCompra orden) {

        orden.setFechaCreacion(LocalDate.now());
        orden.setEstado(EstadoOrden.EMITIDA);

        if (orden.getCliente() != null && orden.getCliente().getId() != null) {
            Cliente existente = clienteRepository.findById(orden.getCliente().getId()).orElse(null);
            orden.setCliente(existente);
        }
        
        // Si hay productos, suma el total del precio y entrégame la lista de esos productos.
        if (orden.getProductos() != null && !orden.getProductos().isEmpty()) {
            double suma = 0; 
            List<Producto> productosDeclarados = new ArrayList<>();

            for (Producto p : orden.getProductos()) {
                Producto productoAlmacenado = productoRepository.findById(p.getId()).orElse(null);
                if (productoAlmacenado != null) {
                    suma += productoAlmacenado.getPrecio();
                    productosDeclarados.add(productoAlmacenado);
                }
            }
            orden.setProductos(productosDeclarados);
            orden.setTotalCompra(suma);
        }

        return ordenCompraRepository.save(orden);
    }

    @Override
    public OrdenCompra updateOrden(Long id, OrdenCompra orden) {
        if (ordenCompraRepository.existsById(id)) {
            OrdenCompra existente = ordenCompraRepository.findById(id).get();

            // Mantengo id y fecha de creación del existente
            orden.setId(id);
            orden.setFechaCreacion(existente.getFechaCreacion());

            // Pregunto si traes los demás datos, sino: dejo los que ya tenía.
            if (orden.getCliente() == null) {
                orden.setCliente(existente.getCliente());
            }

            // Hay que recalcular por si ponen productos con valores nuevos o cambian sus valores.
            if (orden.getProductos() == null || orden.getProductos().isEmpty()) {
                orden.setProductos(existente.getProductos());
                orden.setTotalCompra(existente.getTotalCompra());
            } else {
                double nuevaSuma = 0;
                List<Producto> productosNuevos = new ArrayList<>();
                for (Producto p : orden.getProductos()) {
                    Producto prodExistente = productoRepository.findById(p.getId()).orElse(null);
                    if (prodExistente != null) {
                        nuevaSuma += prodExistente.getPrecio();
                        productosNuevos.add(prodExistente);
                    }
                }
                orden.setProductos(productosNuevos);
                orden.setTotalCompra(nuevaSuma);
            }

            if (orden.getEstado() == null) {
                orden.setEstado(existente.getEstado());
            }

            return ordenCompraRepository.save(orden);
        } else {
            throw new RuntimeException("/PUT id de orden no encontrado para actualizar.");
        }
    }

    @Override
    public void deleteOrden(Long id) {
        if (ordenCompraRepository.findById(id) == null) {
            throw new RuntimeException("/DELETE id de orden no encontrado para eliminar.");
        }
        ordenCompraRepository.deleteById(id);
    }

    @Override
    public List<OrdenCompra> findByEstado(EstadoOrden estado) {
        List<OrdenCompra> todas = ordenCompraRepository.findAll();
        List<OrdenCompra> filtradas = new ArrayList<>();

        for (OrdenCompra ord : todas) {
            if (ord.getEstado().equals(estado)) {
                filtradas.add(ord);
            }
        }

        return filtradas;
    }
}
