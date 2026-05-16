package com.msduoc.ordenesmascotas.controllers.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.service.ClienteService;

@Controller
public class ClienteVistaController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/app/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.getAllClientes());
        model.addAttribute("titulo", "Listado de Clientes");
        return "clientes/listadoClientes";
    }

    @GetMapping("/app/clientes/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("titulo", "Nuevo cliente");
        model.addAttribute("cliente", new Cliente());
        return "clientes/formClientes";
    }

    @PostMapping("/app/clientes/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {

        clienteService.createCliente(cliente);

        return "redirect:/app/clientes";
    }

    @GetMapping("/app/clientes/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.getClienteById(id).get();

        model.addAttribute("titulo", "Editar cliente");
        model.addAttribute("cliente", cliente);

        return "clientes/formClientes";
    }
}
