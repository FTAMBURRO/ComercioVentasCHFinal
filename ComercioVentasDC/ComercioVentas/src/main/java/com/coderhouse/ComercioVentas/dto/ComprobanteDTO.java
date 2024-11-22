package com.coderhouse.ComercioVentas.dto;

import com.coderhouse.ComercioVentas.Model.Comprobante;
import com.coderhouse.ComercioVentas.Model.LineaComprobante;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ComprobanteDTO {

    private Long id;
    private Date fecha;
    private List<LineaComprobanteDTO> lineas;
    private double precioTotal;
    private int cantidadTotal;

    // Constructor
    public ComprobanteDTO(Long id, Date fecha, List<LineaComprobanteDTO> lineas, double precioTotal, int cantidadTotal) {
        this.id = id;
        this.fecha = fecha;
        this.lineas = lineas;
        this.precioTotal = precioTotal;
        this.cantidadTotal = cantidadTotal;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<LineaComprobanteDTO> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaComprobanteDTO> lineas) {
        this.lineas = lineas;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    // Métodos de conversión
    public static ComprobanteDTO fromEntity(Comprobante comprobante) {
        // Convertimos la lista de LineaComprobante a LineaComprobanteDTO
        List<LineaComprobanteDTO> lineasDTO = comprobante.getLineas().stream()
            .map(LineaComprobanteDTO::fromEntity)
            .collect(Collectors.toList());

        // Calculamos el precio total y la cantidad total de productos
        double precioTotal = comprobante.getLineas().stream()
            .mapToDouble(linea -> linea.getPrecioTotal())
            .sum();

        int cantidadTotal = comprobante.getLineas().stream()
            .mapToInt(LineaComprobante::getCantidad)
            .sum();

        return new ComprobanteDTO(
            comprobante.getId(),
            comprobante.getFecha(),
            lineasDTO,
            precioTotal,
            cantidadTotal
        );
    }

    public static Comprobante toEntity(ComprobanteDTO comprobanteDTO) {
        // Convertimos la lista de LineaComprobanteDTO a LineaComprobante
        List<LineaComprobante> lineas = comprobanteDTO.getLineas().stream()
            .map(LineaComprobanteDTO::toEntity)
            .collect(Collectors.toList());

        // Creamos la entidad Comprobante
        Comprobante comprobante = new Comprobante();
        comprobante.setId(comprobanteDTO.getId());
        comprobante.setFecha(comprobanteDTO.getFecha());
        comprobante.setLineas(lineas);

        return comprobante;
    }
}
