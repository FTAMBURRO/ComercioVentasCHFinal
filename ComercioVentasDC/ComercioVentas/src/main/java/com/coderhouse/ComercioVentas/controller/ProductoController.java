package com.coderhouse.ComercioVentas.controller;

import com.coderhouse.ComercioVentas.dto.ProductoDTO;
import com.coderhouse.ComercioVentas.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        Optional<ProductoDTO> producto = productoService.obtenerProductoPorId(id);
        return producto.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody @Valid ProductoDTO productoDTO) {
        ProductoDTO nuevoProducto = productoService.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(nuevoProducto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody @Valid ProductoDTO productoDTO) {
        Optional<ProductoDTO> productoExistente = productoService.obtenerProductoPorId(id);
        if (productoExistente.isPresent()) {
            productoDTO.setId(id); // Mantén el ID consistente
            ProductoDTO productoActualizado = productoService.guardarProducto(productoDTO);
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}


