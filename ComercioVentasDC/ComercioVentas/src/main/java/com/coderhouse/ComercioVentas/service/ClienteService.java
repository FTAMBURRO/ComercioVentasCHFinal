package com.coderhouse.ComercioVentas.service;

import com.coderhouse.ComercioVentas.Model.Cliente;
import com.coderhouse.ComercioVentas.Repository.ClienteRepository;
import com.coderhouse.ComercioVentas.dto.ClienteDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
 // Método para convertir de ClienteDTO a Cliente
    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setDireccion(clienteDTO.getDireccion());
        return cliente;
    }
    
        // Método para convertir de Cliente a ClienteDTO
    private ClienteDTO convertToDTO(Cliente cliente) {
        return new ClienteDTO(
            cliente.getId(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getEmail(),
            cliente.getDireccion(),
            cliente.getTelefono()
        );
    }

    // Obtener todos los clientes
    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    // Obtener cliente por ID
    public Optional<ClienteDTO> obtenerClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(this::convertToDTO); // Devuelve el DTO
    }
    
    // Guardar cliente
    public ClienteDTO guardarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return convertToDTO(savedCliente);
    }
    
    public boolean eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false; // Cliente no encontrado
    }


}
