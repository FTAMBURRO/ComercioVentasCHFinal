package com.coderhouse.ComercioVentas.controller;

import com.coderhouse.ComercioVentas.service.ClienteService;
import com.coderhouse.ComercioVentas.dto.ClienteDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        Optional<ClienteDTO> clienteDTO = clienteService.obtenerClientePorId(id);
        return clienteDTO.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO nuevoClienteDTO = clienteService.guardarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoClienteDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteDTO> clienteExistente = clienteService.obtenerClientePorId(id);
        if (clienteExistente.isPresent()) {
            clienteDTO.setId(id);
            ClienteDTO clienteActualizado = clienteService.guardarCliente(clienteDTO);
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

   

    // Otros métodos para GET, PUT, DELETE según sea necesario
}
