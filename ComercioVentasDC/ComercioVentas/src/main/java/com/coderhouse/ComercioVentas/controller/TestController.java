package com.coderhouse.ComercioVentas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/api/test")
    public String testEndpoint() {
        return "Este es un endpoint de prueba!";
    }
}
