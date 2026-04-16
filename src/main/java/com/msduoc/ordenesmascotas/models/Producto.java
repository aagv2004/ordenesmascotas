package com.msduoc.ordenesmascotas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "precio")
    private double precio;

    public Long getId() {
        return id;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public double getPrecio() {
        return precio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
