package com.coderhouse.ComercioVentas.service;

import com.coderhouse.ComercioVentas.Model.Comprobante;
import com.coderhouse.ComercioVentas.Model.Cliente;
import com.coderhouse.ComercioVentas.Model.Producto;
import com.coderhouse.ComercioVentas.Repository.ComprobanteRepository;
import com.coderhouse.ComercioVentas.Repository.ClienteRepository;
import com.coderhouse.ComercioVentas.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los comprobantes
    public List<Comprobante> obtenerTodosLosComprobantes() {
        return comprobanteRepository.findAll();
    }

    // Obtener un comprobante por ID
    public Optional<Comprobante> obtenerComprobantePorId(Long id) {
        return comprobanteRepository.findById(id);
    }

    // Método para obtener la fecha desde el servicio REST
    private Date obtenerFechaActual() {
        String url = "http://worldclockapi.com/api/json/utc/now";
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                // Si la llamada al servicio es exitosa, extraemos la fecha (en formato JSON).
                // Aquí se debería procesar la respuesta para obtener la fecha.
                return new Date(); // Cambia esto para extraer correctamente la fecha de la respuesta.
            }
        } catch (Exception e) {
            // Si falla la llamada al servicio REST, usamos la fecha actual local.
            System.out.println("Error obteniendo fecha desde el servicio REST, usando fecha local.");
        }
        return new Date(); // Fecha actual local si el servicio REST falla
    }

    // Guardar un nuevo comprobante con validaciones
    public Comprobante guardarComprobante(Comprobante comprobante) {
        // Validación: Verificar si el cliente existe
        Optional<Cliente> clienteOpt = clienteRepository.findById(comprobante.getCliente().getId());
        if (!clienteOpt.isPresent()) {
            throw new IllegalArgumentException("El cliente no existe.");
        }

        // Calcular el precio total y la cantidad de productos
        double precioTotal = 0;
        int cantidadTotal = 0;

        // Validación: Verificar si los productos existen y si la cantidad solicitada es válida
        for (Producto producto : comprobante.getProducto()) {
            Optional<Producto> productoOpt = productoRepository.findById(producto.getId());
            if (!productoOpt.isPresent()) {
                throw new IllegalArgumentException("El producto con ID " + producto.getId() + " no existe.");
            }
            // Verificar si la cantidad solicitada no excede el stock disponible
            Producto productoExistente = productoOpt.get();
            if (producto.getCantidadSolicitada() > productoExistente.getStock()) {
                throw new IllegalArgumentException("La cantidad solicitada del producto " + producto.getNombre() + " excede el stock disponible.");
            }

            // Reducir el stock en la cantidad de unidades vendidas
            productoExistente.setStock(productoExistente.getStock() - producto.getCantidadSolicitada());
            productoRepository.save(productoExistente);  // Actualizamos el stock del producto

            // Calcular el precio total y la cantidad de productos
            precioTotal += productoExistente.getPrecio() * producto.getCantidadSolicitada();
            cantidadTotal += producto.getCantidadSolicitada();
        }

        // Establecer la fecha en el comprobante (desde el servicio REST o local)
        comprobante.setFecha(obtenerFechaActual());
        comprobante.setPrecioTotal(precioTotal);
        comprobante.setCantidadProductos(cantidadTotal);

        // Guardar el comprobante (no modificamos los comprobantes anteriores, solo creamos nuevos)
        return comprobanteRepository.save(comprobante);
    }

    // Eliminar un comprobante por ID
    public void eliminarComprobante(Long id) {
        comprobanteRepository.deleteById(id);
    }

    // Método adicional para manejar el cambio de precio sin modificar comprobantes anteriores
    public void actualizarPrecioProducto(Long productoId, Double nuevoPrecio) {
        Optional<Producto> productoOpt = productoRepository.findById(productoId);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            producto.setPrecio(nuevoPrecio);  // Actualizamos el precio
            productoRepository.save(producto);
        } else {
            throw new IllegalArgumentException("El producto con ID " + productoId + " no existe.");
        }
    }
}



