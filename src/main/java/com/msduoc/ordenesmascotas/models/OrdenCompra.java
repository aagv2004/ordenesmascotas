package com.msduoc.ordenesmascotas.models;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.msduoc.ordenesmascotas.enums.EstadoOrden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "orden_compra")
public class OrdenCompra {

    // ========== Columnas ==========
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orden_id")
    private Long id;

    @Column(name = "fecha_creacion", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;

    @NotNull(message = "Estado es un campo obligatorio.")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoOrden estado;

    @NotNull(message = "Esta orden DEBE pertenecer a un cliente.")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotEmpty(message = "Esta orden DEBE tener productos, no puede estar vacía.")
    @ManyToMany
    @JoinTable(
        name = "orden_productos", 
        joinColumns = @JoinColumn(name = "orden_id"), 
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    @Positive(message = "El total de compra debe ser mayor a cero.")
    @Column(name = "total_compra")
    private double totalCompra;

    // ========== Getters ==========
    public Long getId() {
        return id;
    }
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    public EstadoOrden getEstadoOrden() {
        return estado;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public double getTotalCompra() {
        return totalCompra;
    }

    // ========== Setters ==========

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }
}
