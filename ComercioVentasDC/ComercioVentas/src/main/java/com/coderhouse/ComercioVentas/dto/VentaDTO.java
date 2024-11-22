package com.coderhouse.ComercioVentas.dto;

import com.coderhouse.ComercioVentas.Model.Cliente;
import com.coderhouse.ComercioVentas.Model.Comprobante;
import com.coderhouse.ComercioVentas.Model.Venta;

import java.sql.Date;

public class VentaDTO {

    private Long id;
    private ClienteDTO cliente; // Usamos el DTO de Cliente
    private Date fecha;
    private ComprobanteDTO comprobante; // Usamos el DTO de Comprobante

    // Constructor
    public VentaDTO(Long id, ClienteDTO cliente, Date fecha, ComprobanteDTO comprobante) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.comprobante = comprobante;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ComprobanteDTO getComprobante() {
        return comprobante;
    }

    public void setComprobante(ComprobanteDTO comprobante) {
        this.comprobante = comprobante;
    }

    // Métodos de conversión
    public static VentaDTO fromEntity(Venta venta) {
        // Convertir cliente y comprobante a sus respectivos DTOs
        ClienteDTO clienteDTO = ClienteDTO.fromEntity(venta.getCliente());
        ComprobanteDTO comprobanteDTO = ComprobanteDTO.fromEntity(venta.getComprobante());

        return new VentaDTO(
            venta.getId(),
            clienteDTO,
            venta.getFecha(),
            comprobanteDTO
        );
    }

    public static Venta toEntity(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        // Convertir cliente y comprobante a sus respectivas entidades
        Cliente cliente = ClienteDTO.toEntity(ventaDTO.getCliente());
        Comprobante comprobante = ComprobanteDTO.toEntity(ventaDTO.getComprobante());

        venta.setId(ventaDTO.getId());
        venta.setCliente(cliente);
        venta.setFecha(ventaDTO.getFecha());
        venta.setComprobante(comprobante);

        return venta;
    }
}
