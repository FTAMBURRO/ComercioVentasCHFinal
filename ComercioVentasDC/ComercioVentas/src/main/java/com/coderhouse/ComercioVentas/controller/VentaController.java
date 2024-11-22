package com.coderhouse.ComercioVentas.controller;

import com.coderhouse.ComercioVentas.dto.VentaDTO;
import com.coderhouse.ComercioVentas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Obtener todas las ventas como DTO
    @GetMapping
    public List<VentaDTO> obtenerTodasLasVentas() {
        return ventaService.obtenerTodasLasVentas();
    }

    // Obtener una venta por ID como DTO
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerVentaPorId(@PathVariable Long id) {
        Optional<VentaDTO> venta = ventaService.obtenerVentaPorId(id);
        return venta.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva venta, pasando un DTO y devolviendo un DTO
    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO ventaDTO) {
        VentaDTO nuevaVenta = ventaService.guardarVenta(ventaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    // Actualizar una venta, pasando un DTO y devolviendo un DTO actualizado
    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        Optional<VentaDTO> ventaExistente = ventaService.obtenerVentaPorId(id);
        if (ventaExistente.isPresent()) {
            ventaDTO.setId(id);
            VentaDTO ventaActualizada = ventaService.guardarVenta(ventaDTO);
            return ResponseEntity.ok(ventaActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Eliminar una venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
