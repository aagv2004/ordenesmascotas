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
        // Entregar el campo fecha creación que sirve cómo auditoría y 
        // el estado como EMITIDA automáticamente si se prefiere cambiar luego se hace un put.
        orden.setFechaCreacion(LocalDate.now());
        orden.setEstado(EstadoOrden.EMITIDA);

        // Verificamos que exista el cliente en el request, luego en bd y lo seteamos en la orden.
        if (orden.getCliente() != null && orden.getCliente().getId() != null) {
            Cliente existente = clienteRepository.findById(orden.getCliente().getId()).orElse(null);
            orden.setCliente(existente);
        }
        
        // Si hay productos, suma el total del precio, entrégame la lista de esos productos 
        // y setealos en la orden.
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

            // Pregunto si me traes los demás datos, sino: dejo los que ya tenía.
            if (orden.getCliente() == null) {
                orden.setCliente(existente.getCliente());
            }

            if (orden.getProductos() == null || orden.getProductos().isEmpty()) {
                orden.setProductos(existente.getProductos());
            }

            if (orden.getEstado() == null) {
                orden.setEstado(existente.getEstado());
            }

            if (orden.getTotalCompra() == 0) {
                orden.setTotalCompra(existente.getTotalCompra());
            }


            return ordenCompraRepository.save(orden);
        } else {
            return null;
        }
    }

    @Override
    public void deleteOrden(Long id) {
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
