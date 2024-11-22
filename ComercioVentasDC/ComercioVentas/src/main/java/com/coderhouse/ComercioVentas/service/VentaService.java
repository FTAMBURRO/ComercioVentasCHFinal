package com.coderhouse.ComercioVentas.service;

import com.coderhouse.ComercioVentas.Model.Venta;
import com.coderhouse.ComercioVentas.Repository.VentaRepository;
import com.coderhouse.ComercioVentas.dto.VentaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    // Obtener todas las ventas como DTO
    public List<VentaDTO> obtenerTodasLasVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        // Convertimos cada entidad Venta a un DTO
        return ventas.stream()
            .map(VentaDTO::fromEntity)
            .collect(Collectors.toList());
    }

    // Obtener una venta por su ID como DTO
    public Optional<VentaDTO> obtenerVentaPorId(Long id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        // Convertimos la entidad a DTO si existe
        return venta.map(VentaDTO::fromEntity);
    }

    // Guardar una venta, pasando el DTO y devolviendo el DTO guardado
    public VentaDTO guardarVenta(VentaDTO ventaDTO) {
        // Convertimos el DTO a entidad
        Venta venta = VentaDTO.toEntity(ventaDTO);
        // Guardamos la entidad en la base de datos
        Venta ventaGuardada = ventaRepository.save(venta);
        // Devolvemos el DTO de la venta guardada
        return VentaDTO.fromEntity(ventaGuardada);
    }

    // Eliminar una venta por ID
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}
