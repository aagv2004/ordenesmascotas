package com.msduoc.ordenesmascotas.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msduoc.ordenesmascotas.models.OrdenCompra;
import com.msduoc.ordenesmascotas.models.Producto;
import com.msduoc.ordenesmascotas.repository.OrdenCompraRepository;
import com.msduoc.ordenesmascotas.repository.ProductoRepository;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

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

        if (orden.getProductos() != null && !orden.getProductos().isEmpty()) {
            double suma = 0; 
            for (Producto p : orden.getProductos()) {
                Producto productoAlmacenado = productoRepository.findById(p.getId()).orElse(null);
                if (productoAlmacenado != null) {
                    suma += productoAlmacenado.getPrecio();
                }
            }
            orden.setTotalCompra(suma);
        }
        return ordenCompraRepository.save(orden);
    }

    @Override
    public OrdenCompra updateOrden(Long id, OrdenCompra orden) {
        if (ordenCompraRepository.existsById(id)) {
            orden.setId(id);
            return ordenCompraRepository.save(orden);
        } else {
            return null;
        }
    }

    @Override
    public void deleteOrden(Long id) {
        ordenCompraRepository.deleteById(id);
    }
}
