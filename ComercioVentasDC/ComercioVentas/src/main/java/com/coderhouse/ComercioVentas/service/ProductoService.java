package com.coderhouse.ComercioVentas.service;

import com.coderhouse.ComercioVentas.Model.Producto;
import com.coderhouse.ComercioVentas.Repository.ProductoRepository;
import com.coderhouse.ComercioVentas.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear Producto (ahora recibiendo ProductoDTO)
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        // Convertimos el ProductoDTO a Producto entidad
        Producto producto = ProductoDTO.toEntity(productoDTO);
        // Guardamos la entidad en el repositorio
        Producto productoGuardado = productoRepository.save(producto);
        // Devolvemos el ProductoDTO convertido
        return ProductoDTO.fromEntity(productoGuardado);
    }

    // Obtener todos los productos (ahora devuelve una lista de ProductoDTO)
    public List<ProductoDTO> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        // Convertimos todos los productos a ProductoDTO
        return productos.stream()
                        .map(ProductoDTO::fromEntity)
                        .collect(Collectors.toList());
    }

    // Obtener un producto por ID (ahora devuelve un ProductoDTO)
    public Optional<ProductoDTO> obtenerProductoPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(ProductoDTO::fromEntity);
    }

    // Guardar Producto (ahora recibiendo y devolviendo ProductoDTO)
    public ProductoDTO guardarProducto(ProductoDTO productoDTO) {
        // Convertimos el ProductoDTO a Producto entidad
        Producto producto = ProductoDTO.toEntity(productoDTO);
        // Guardamos la entidad en el repositorio
        Producto productoGuardado = productoRepository.save(producto);
        // Devolvemos el ProductoDTO convertido
        return ProductoDTO.fromEntity(productoGuardado);
    }

    // Eliminar producto (no cambia, ya que solo se necesita el ID)
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}

