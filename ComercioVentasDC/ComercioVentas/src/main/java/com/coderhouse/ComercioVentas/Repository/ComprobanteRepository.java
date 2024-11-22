package com.coderhouse.ComercioVentas.Repository;

import com.coderhouse.ComercioVentas.Model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
    // Métodos personalizados si son necesarios
}
