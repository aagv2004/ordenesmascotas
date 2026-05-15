package com.msduoc.ordenesmascotas.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.msduoc.ordenesmascotas.service.ProductoService;

@Controller
public class ProductoVistaController {

    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/app/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        model.addAttribute("titulo", "Listado de Productos");
        return "productos/listadoProductos";
    }
}
