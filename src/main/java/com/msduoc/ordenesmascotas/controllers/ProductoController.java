package com.msduoc.ordenesmascotas.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.msduoc.ordenesmascotas.models.Producto;
import com.msduoc.ordenesmascotas.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ProductoService productoService;

    // Endpoints: GET
    @GetMapping
    public List<Producto> getAllProductos() {
        log.info("/GET todos los productos.");
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> getProductoById(@PathVariable Long id) {
        log.info("/GET detalle producto id: {}", id);
        return productoService.getProductoById(id);
    }

    // Endpoints: POST
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        log.info("/POST creando producto: {}, con precio: ${}", producto.getNombreProducto(), producto.getPrecio());
        return productoService.createProducto(producto);
    }

    // Endpoints: PUT
    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        log.info("/PUT actualizando producto con id: {}", id);
        return productoService.updateProducto(id, producto);
    }

    // Endpoints: DELETE
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        log.warn("/DELETE eliminando producto con id: {}", id);
        productoService.deleteProducto(id);
    }
}
