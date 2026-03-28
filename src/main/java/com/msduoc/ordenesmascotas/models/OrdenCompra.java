package com.msduoc.ordenesmascotas.models;

import java.util.ArrayList;
import java.util.List;
import com.msduoc.ordenesmascotas.enums.EstadoOrden;

public class OrdenCompra {
    private int id;
    private String fechaCreacion;
    private EstadoOrden estado;
    private Cliente cliente;
    private List<Producto> productos = new ArrayList<>();
    private double totalCompra;

    public OrdenCompra(int id, String fechaCreacion, EstadoOrden estado, Cliente cliente, List<Producto> productos){
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.cliente = cliente;
        this.productos = productos;

        double suma = 0;
        for (Producto prod : productos) {
            suma += prod.getPrecio();
        }
        this.totalCompra = suma;
    }

    public int getId() {
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
