package com.coderhouse.ComercioVentas.Repository;

import com.coderhouse.ComercioVentas.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    // Métodos personalizados si son necesarios
}

