package com.msduoc.ordenesmascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.msduoc.ordenesmascotas.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
