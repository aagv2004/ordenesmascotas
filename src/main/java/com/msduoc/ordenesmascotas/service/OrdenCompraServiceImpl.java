package com.msduoc.ordenesmascotas.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.jetbrains.exported.JBRApi.Service;
import com.msduoc.ordenesmascotas.models.OrdenCompra;
import com.msduoc.ordenesmascotas.repository.OrdenCompraRepository;

@Service
public class OrdenCompraServiceImpl implements OrdenCompraService {
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

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
