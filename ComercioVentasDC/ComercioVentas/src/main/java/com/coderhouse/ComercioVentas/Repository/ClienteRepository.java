package com.coderhouse.ComercioVentas.Repository;

import com.coderhouse.ComercioVentas.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aquí puedes definir consultas personalizadas si es necesario
}