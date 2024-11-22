package com.coderhouse.ComercioVentas.dto;

import com.coderhouse.ComercioVentas.Model.Producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El precio no puede estar vacío")
    @Positive(message = "El precio debe ser positivo")
    private Double precio;

    @NotNull(message = "El stock no puede estar vacío")
    @Positive(message = "El stock debe ser positivo")
    private Integer stock;

    // Constructor vacío
    public ProductoDTO() {}

    // Constructor con parámetros
    public ProductoDTO(Long id, String nombre, Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    // Métodos de conversión
    public static ProductoDTO fromEntity(Producto producto) {
        return new ProductoDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getPrecio(),
            producto.getStock()
        );
    }

    public static Producto toEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        return producto;
    }
}
