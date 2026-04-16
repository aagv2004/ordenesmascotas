package com.msduoc.ordenesmascotas.models;

import java.util.ArrayList;
import java.util.List;
import com.msduoc.ordenesmascotas.enums.EstadoOrden;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordenCompra")
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fechaCreacion")
    private String fechaCreacion;

    @Column(name = "estado")
    private EstadoOrden estado;

    @Column(name = "cliente")
    private Cliente cliente;

    @Column(name = "productos")
    private List<Producto> productos = new ArrayList<>();

    @Column(name = "totalCompra")
    private double totalCompra;


    public Long getId() {
        return id;
    }
    public String getFechaCreacion() {
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

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setFechaCreacion(String fechaCreacion) {
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
