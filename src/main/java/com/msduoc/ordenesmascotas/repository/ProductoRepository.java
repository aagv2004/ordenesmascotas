package com.msduoc.ordenesmascotas.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.msduoc.ordenesmascotas.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
