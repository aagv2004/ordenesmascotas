package com.msduoc.ordenesmascotas.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.msduoc.ordenesmascotas.enums.EstadoOrden;
import com.msduoc.ordenesmascotas.models.OrdenCompra;
import com.msduoc.ordenesmascotas.service.OrdenCompraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ordenes")
public class OrdenCompraController {
    private static final Logger log = LoggerFactory.getLogger(OrdenCompraController.class);

    @Autowired
    private OrdenCompraService ordenCompraService;

    // Endpoints: GET

    @GetMapping
    public List<OrdenCompra> getOrdenes() {
        log.info("/GET todas las ordenes.");
        return ordenCompraService.getAllOrdenes();
    }

    @GetMapping("/{id}")
    public Optional<OrdenCompra> getOrdenCompraPorId(@PathVariable Long id) {
        log.info("/GET detalle orden id: {}", id);
        return ordenCompraService.getOrdenById(id);
    }

    // Estados: EMITIDA, ACEPTADA, RECHAZADA, RECIBIDA (todos en mayúsculas).
    @GetMapping("/estado")
    public List<OrdenCompra> getOrdenesPorEstado(@RequestParam EstadoOrden estado) {
        log.info("/GET ordenes según el estado: {}", estado);
        List<OrdenCompra> filtradas = new ArrayList<>();

        for (OrdenCompra ord : ordenCompraService.getAllOrdenes()) {
            if (ord.getEstadoOrden() == estado) {
                filtradas.add(ord);
            }
        }
        return filtradas;
    }

    // Endpoints: POST
    @PostMapping
    public OrdenCompra creaOrden(@RequestBody OrdenCompra orden) {
        log.info("/POST creando orden para cliente ID: {}", orden.getCliente().getId());
        return ordenCompraService.createOrden(orden);
    }

    // Endpoints: PUT
    @PutMapping("/{id}")
    public OrdenCompra updateOrden(@PathVariable Long id, @RequestBody OrdenCompra orden) {
        log.info("/PUT actualizando orden con id: {}", id);
        return ordenCompraService.updateOrden(id, orden);
    }

    // Endpoints: DELETE

    @DeleteMapping("/{id}")
    public void deleteOrden(@PathVariable Long id) {
        log.warn("/DELETE eliminando orden con id: {}", id);
        ordenCompraService.deleteOrden(id);
    }
}
