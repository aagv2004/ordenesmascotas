package com.msduoc.ordenesmascotas.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.msduoc.ordenesmascotas.enums.EstadoOrden;

import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.models.OrdenCompra;
import com.msduoc.ordenesmascotas.models.Producto;

@RestController
public class OrdenCompraController {
    private List<OrdenCompra> ordenesmascotas = new ArrayList<>();

    public OrdenCompraController() {
        // Poblado de información

        // Acá voy a crear diferentes "packs" de productos genéricos para animales,
        // de esta manera puedo luego reutilizarlos sin repetir esto infinitamente.
        List<Producto> packGatos = List.of(
            new Producto(1, "Arena sanitaria (4kg)", 6500),
            new Producto(2, "Shampoo de espuma en seco", 8990),
            new Producto(3, "Cepillo Cardina para pelo", 4500)
        );
        List<Producto> packPerros = List.of(
            new Producto(4, "Shampoo hipoalergénico (500ml)", 7990),
            new Producto(5, "Cortauñas tipo guillotina", 5500),
            new Producto(6, "Cepillo de dientes + pasta dental", 6000)
        );
        List<Producto> packConejos = List.of(
            new Producto(7, "Sustrato de papel reciclado (10L)", 8500),
            new Producto(8, "Peine de cerdas suaves", 3990),
            new Producto(9, "Heno de alfalfa premium (500g)", 4200)
        );

        // Acá voy a crear los diferentes clientes, lamentablemente no puedo hacer packs de clientes
        // así que para no complicarme simplemente creé a los 8 clientes y ya.

        Cliente c1 = new Cliente(1, "Alejandro", "González");
        Cliente c2 = new Cliente(2, "Pedro", "González");  
        Cliente c3 = new Cliente(3, "Eduardo", "Vergara");  
        Cliente c4 = new Cliente(4, "Juan", "Rodríguez");  
        Cliente c5 = new Cliente(5, "Diego", "Alcayaga");  
        Cliente c6 = new Cliente(6, "Thiare", "Alquinta");  
        Cliente c7 = new Cliente(7, "Dalia", "Ramirez");  
        Cliente c8 = new Cliente(8, "Elsa", "Pato");  

        // Finalmente poblamos las ordenesCompra usando los clientes anteriormente creados
        // y los packs de productos para mascotas.

        ordenesmascotas.add(new OrdenCompra(1, "21/10/2024", EstadoOrden.EMITIDA, c1, packGatos));
        ordenesmascotas.add(new OrdenCompra(2, "22/10/2024", EstadoOrden.ACEPTADA, c2, packPerros));
        ordenesmascotas.add(new OrdenCompra(3, "23/10/2024", EstadoOrden.RECIBIDA, c3, packConejos));
        ordenesmascotas.add(new OrdenCompra(4, "24/10/2024", EstadoOrden.RECHAZADA, c4, packPerros));
        ordenesmascotas.add(new OrdenCompra(5, "25/10/2024", EstadoOrden.EMITIDA, c5, packGatos));
        ordenesmascotas.add(new OrdenCompra(6, "26/10/2024", EstadoOrden.RECHAZADA, c6, packConejos));
        ordenesmascotas.add(new OrdenCompra(7, "27/10/2024", EstadoOrden.RECIBIDA, c7, packGatos));
        ordenesmascotas.add(new OrdenCompra(8, "28/10/2024", EstadoOrden.RECIBIDA, c8, packPerros));
    }

    @GetMapping("/ordenesmascotas")
    public List<OrdenCompra> getOrdenes() {
        return ordenesmascotas;
    }

    @GetMapping("/ordenesmascotas/{id}")
    public OrdenCompra getOrdenCompraPorId(@PathVariable int id) {
        for (OrdenCompra ord : ordenesmascotas) {
            if (ord.getId() == id) {
                return ord;
            }
        }
        return null;
    }

    // Estados: EMITIDA, ACEPTADA, RECHAZADA, RECIBIDA (todos en mayúsculas).
    @GetMapping("/ordenesmascotas/estado")
    public List<OrdenCompra> getOrdenesPorEstado(@RequestParam EstadoOrden estado) {
        List<OrdenCompra> filtradas = new ArrayList<>();

        for (OrdenCompra ord : ordenesmascotas) {
            if (ord.getEstadoOrden() == estado) {
                filtradas.add(ord);
            }
        }
        return filtradas;
    }
}
