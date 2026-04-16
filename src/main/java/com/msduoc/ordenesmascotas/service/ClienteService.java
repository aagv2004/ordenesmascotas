package com.msduoc.ordenesmascotas.service;

import java.util.List;
import java.util.Optional;
import com.msduoc.ordenesmascotas.models.Cliente;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Optional<Cliente> getClienteById(Long id);
    Cliente createCliente(Cliente cliente);
    Cliente updateCliente(Long id, Cliente cliente);
    void deleteCliente(Long id);
}
