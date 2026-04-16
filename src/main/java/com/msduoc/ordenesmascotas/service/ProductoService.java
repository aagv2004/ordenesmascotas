package com.msduoc.ordenesmascotas.service;

import java.util.List;
import java.util.Optional;
import com.msduoc.ordenesmascotas.models.Producto;

public interface ProductoService {
    List<Producto> getAllProductos();
    Optional<Producto> getProductoById(Long id);
    Producto createProducto(Producto producto);
    Producto updateProducto(Long id, Producto producto);
    void deleteProducto(Long id);
}
