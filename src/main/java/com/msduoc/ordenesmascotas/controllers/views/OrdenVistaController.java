package com.msduoc.ordenesmascotas.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.msduoc.ordenesmascotas.models.OrdenCompra;
import com.msduoc.ordenesmascotas.service.ClienteService;
import com.msduoc.ordenesmascotas.service.OrdenCompraService;
import com.msduoc.ordenesmascotas.service.ProductoService;
import org.springframework.ui.Model;
import com.msduoc.ordenesmascotas.enums.EstadoOrden;

@Controller
public class OrdenVistaController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/app/ordenes")
    public String listarOrdenes(Model model) {
        model.addAttribute("ordenes", ordenCompraService.getAllOrdenes());
        model.addAttribute("titulo", "Listado de Ordenes de Compra");
        return "ordenes/listadoOrdenes";
    }

    @GetMapping("/app/ordenes/nuevo")
    public String nuevaOrden(Model model) {
        model.addAttribute("titulo", "Nueva orden de compra");
        model.addAttribute("estadoOrden", EstadoOrden.values());
        model.addAttribute("orden", new OrdenCompra());
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("productos", productoService.getAllProductos());
        return "ordenes/formOrdenes";
    }

    
    @GetMapping("/app/ordenes/editar/{id}")
    public String editarOrden(@PathVariable Long id, Model model) {
        OrdenCompra orden = ordenCompraService.getOrdenById(id).get();
        
        model.addAttribute("titulo", "Editar orden de compra");
        model.addAttribute("estadoOrden", EstadoOrden.values());
        model.addAttribute("orden", orden);
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("productos", productoService.getAllProductos());
        
        return "ordenes/formOrdenes";
    }
    
    @PostMapping("/app/ordenes/guardar")
    public String guardarOrden(@ModelAttribute OrdenCompra orden) {

        if (orden.getId() != null) {
            ordenCompraService.updateOrden(orden.getId(), orden);
        } else {
        
            ordenCompraService.createOrden(orden);
        }
        return "redirect:/app/ordenes";
    }
}
