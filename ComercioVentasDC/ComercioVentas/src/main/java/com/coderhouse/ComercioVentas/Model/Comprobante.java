package com.coderhouse.ComercioVentas.Model;

import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comprobante_id")
    private List<LineaComprobante> lineas;

    // Constructor por defecto
    public Comprobante() {}

    // Constructor con parámetros
    public Comprobante(Date fecha, List<LineaComprobante> lineas) {
        this.fecha = fecha;
        this.lineas = lineas;
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

    public List<LineaComprobante> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaComprobante> lineas) {
        this.lineas = lineas;
    }

	public Comprobante getCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	public Producto[] getProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPrecioTotal(double precioTotal) {
		// TODO Auto-generated method stub
		
	}

	public void setCantidadProductos(int cantidadTotal) {
		// TODO Auto-generated method stub
		
	}
}
