package com.msduoc.ordenesmascotas.controllers;

// import java.util.ArrayList;
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
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import com.msduoc.ordenesmascotas.enums.EstadoOrden;

// import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.models.OrdenCompra;
// import com.msduoc.ordenesmascotas.models.Producto;
import com.msduoc.ordenesmascotas.service.OrdenCompraService;

@RestController
@RequestMapping("/ordenes")
public class OrdenCompraController {
    @Autowired
    private OrdenCompraService ordenCompraService;

    // Endpoints: GET

    @GetMapping
    public List<OrdenCompra> getOrdenes() {
        return ordenCompraService.getAllOrdenes();
    }

    @GetMapping("/{id}")
    public Optional<OrdenCompra> getOrdenCompraPorId(@PathVariable Long id) {
        return ordenCompraService.getOrdenById(id);
    }

    // // Estados: EMITIDA, ACEPTADA, RECHAZADA, RECIBIDA (todos en mayúsculas).
    // @GetMapping("/estado")
    // public List<OrdenCompra> getOrdenesPorEstado(@RequestParam EstadoOrden estado) {
    //     List<OrdenCompra> filtradas = new ArrayList<>();

    //     for (OrdenCompra ord : ordenesmascotas) {
    //         if (ord.getEstadoOrden() == estado) {
    //             filtradas.add(ord);
    //         }
    //     }
    //     return filtradas;
    // }

    // Endpoints: POST
    @PostMapping
    public OrdenCompra creaOrden(@RequestBody OrdenCompra orden) {
        return ordenCompraService.createOrden(orden);
    }

    // Endpoints: PUT
    @PutMapping("/{id}")
    public OrdenCompra updateOrden(@PathVariable Long id, @RequestBody OrdenCompra orden) {
        return ordenCompraService.updateOrden(id, orden);
    }

    // Endpoints: DELETE

    @DeleteMapping("/{id}")
    public void deleteOrden(@PathVariable Long id) {
        ordenCompraService.deleteOrden(id);
    }
}
