package com.msduoc.ordenesmascotas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long id;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9.\\-° ]+$", message = "El nombre del producto contiene caracteres no permitidos.")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Positive(message = "El precio no puede ser un número menor a cero.")
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
