package com.coderhouse.ComercioVentas.Repository;

import com.coderhouse.ComercioVentas.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Puedes definir métodos de consulta personalizados aquí
}
