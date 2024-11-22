package com.coderhouse.ComercioVentas.controller;

import com.coderhouse.ComercioVentas.Model.Comprobante;
import com.coderhouse.ComercioVentas.service.ComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @GetMapping
    public List<Comprobante> obtenerTodosLosComprobantes() {
        return comprobanteService.obtenerTodosLosComprobantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comprobante> obtenerComprobantePorId(@PathVariable Long id) {
        Optional<Comprobante> comprobante = comprobanteService.obtenerComprobantePorId(id);
        return comprobante.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Comprobante> crearComprobante(@RequestBody Comprobante comprobante) {
        Comprobante nuevoComprobante = comprobanteService.guardarComprobante(comprobante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComprobante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comprobante> actualizarComprobante(@PathVariable Long id, @RequestBody Comprobante comprobante) {
        Optional<Comprobante> comprobanteExistente = comprobanteService.obtenerComprobantePorId(id);
        if (comprobanteExistente.isPresent()) {
            comprobante.setId(id);
            Comprobante comprobanteActualizado = comprobanteService.guardarComprobante(comprobante);
            return ResponseEntity.ok(comprobanteActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComprobante(@PathVariable Long id) {
        comprobanteService.eliminarComprobante(id);
        return ResponseEntity.noContent().build();
    }
}
