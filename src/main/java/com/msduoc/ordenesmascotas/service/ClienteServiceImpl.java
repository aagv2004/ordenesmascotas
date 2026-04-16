package com.msduoc.ordenesmascotas.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msduoc.ordenesmascotas.models.Cliente;
import com.msduoc.ordenesmascotas.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        if (clienteRepository.findAll().isEmpty()) {
            throw new RuntimeException("/GET no hay clientes.");
        }
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Long id) {
        if (clienteRepository.findById(id) == null) {
            throw new RuntimeException("/GET id no encontrado para mostrar detalle cliente.");
        }
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("/PUT id no encontrado para actualizar cliente.");
        }
    }

    @Override
    public void deleteCliente(Long id) {
        if (clienteRepository.findById(id) == null) {
            throw new RuntimeException("/DELETE id no encontrado para eliminar cliente.");
        }
        clienteRepository.deleteById(id);
    }
}
