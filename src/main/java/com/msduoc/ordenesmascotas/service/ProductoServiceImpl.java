package com.msduoc.ordenesmascotas.service;


import com.msduoc.ordenesmascotas.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msduoc.ordenesmascotas.models.Producto;
import com.msduoc.ordenesmascotas.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Producto> getAllProductos() {
        if (clienteRepository.findAll().isEmpty()) {
            throw new RuntimeException("/GET no hay productos.");
        }
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long id) {
        if (clienteRepository.findById(id) == null) {
            throw new RuntimeException("/GET id no encontrado para mostrar detalle producto.");
        }
        return productoRepository.findById(id);
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            return productoRepository.save(producto);
        } else {
            throw new RuntimeException("/PUT id no encontrado para actualizar producto.");
        }
    }

    @Override
    public void deleteProducto(Long id) {
        if (clienteRepository.findById(id) == null) {
            throw new RuntimeException("/DELETE id no encontrado para eliminar producto.");
        }
        productoRepository.deleteById(id);
    }
}
