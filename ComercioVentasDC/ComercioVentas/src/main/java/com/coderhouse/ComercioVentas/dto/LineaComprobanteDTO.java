package com.coderhouse.ComercioVentas.dto;

import com.coderhouse.ComercioVentas.Model.LineaComprobante;

public class LineaComprobanteDTO {

    private Long id;
    private String productoNombre;
    private int cantidad;
    private double precioTotal;

    // Constructor
    public LineaComprobanteDTO(Long id, String productoNombre, int cantidad, double precioTotal) {
        this.id = id;
        this.productoNombre = productoNombre;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    // Métodos de conversión
    public static LineaComprobanteDTO fromEntity(LineaComprobante lineaComprobante) {
        return new LineaComprobanteDTO(
            lineaComprobante.getId(),
            lineaComprobante.getProducto().getNombre(), // Asumiendo que Producto tiene un nombre
            lineaComprobante.getCantidad(),
            lineaComprobante.getPrecioTotal()
        );
    }

    public static LineaComprobante toEntity(LineaComprobanteDTO lineaComprobanteDTO) {
        LineaComprobante lineaComprobante = new LineaComprobante();
        lineaComprobante.setId(lineaComprobanteDTO.getId());
        // Aquí asumimos que ya tienes la lógica para obtener un Producto
        // lineaComprobante.setProducto(obtenerProducto(lineaComprobanteDTO.getProductoId()));
        lineaComprobante.setCantidad(lineaComprobanteDTO.getCantidad());
        lineaComprobante.setPrecioTotal(lineaComprobanteDTO.getPrecioTotal());
        return lineaComprobante;
    }
}
