package com.msduoc.ordenesmascotas.service;

import java.util.List;
import java.util.Optional;
import com.msduoc.ordenesmascotas.models.OrdenCompra;

public interface OrdenCompraService {
    List<OrdenCompra> getAllOrdenes();
    Optional<OrdenCompra> getOrdenById(Long id);
    OrdenCompra createOrden(OrdenCompra orden);
    OrdenCompra updateOrden(Long id, OrdenCompra orden);
    void deleteOrden(Long id);
}
