package com.msduoc.ordenesmascotas.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.msduoc.ordenesmascotas.service.OrdenCompraService;
import org.springframework.ui.Model;

@Controller
public class OrdenVistaController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @GetMapping("/app/ordenes")
    public String listarOrdenes(Model model) {
        model.addAttribute("ordenes", ordenCompraService.getAllOrdenes());
        model.addAttribute("titulo", "Listado de Órdenes de Compra");
        return "ordenes/listadoOrdenes";
    }
}
