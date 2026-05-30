package com.pesquera.vedas.controller;

import com.pesquera.vedas.dto.VedaRequestDTO;
import com.pesquera.vedas.model.Veda;
import com.pesquera.vedas.service.VedaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vedas")
@RequiredArgsConstructor
public class VedaController {

    private final VedaService vedaService;

    @PostMapping
    public ResponseEntity<Veda> crearVeda(@Valid @RequestBody VedaRequestDTO dto) {
        Veda veda = new Veda();
        veda.setEspecie(dto.getEspecie());
        veda.setZona(dto.getZona());
        veda.setMotivoVeda(dto.getMotivoVeda());
        veda.setDecretadaPor(dto.getDecretadaPor());
        return ResponseEntity.status(HttpStatus.CREATED).body(vedaService.crearVeda(veda));
    }

    @GetMapping
    public ResponseEntity<List<Veda>> obtenerTodas() {
        return ResponseEntity.ok(vedaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veda> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vedaService.obtenerPorId(id));
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Veda>> obtenerActivas() {
        return ResponseEntity.ok(vedaService.obtenerActivas());
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Veda>> obtenerPorEspecie(@PathVariable String especie) {
        return ResponseEntity.ok(vedaService.obtenerPorEspecie(especie));
    }

    @GetMapping("/verificar/{especie}")
    public ResponseEntity<Boolean> verificarVeda(@PathVariable String especie) {
        return ResponseEntity.ok(vedaService.especieEnVeda(especie));
    }

    @PutMapping("/{id}/suspender")
    public ResponseEntity<Veda> suspenderVeda(@PathVariable Long id) {
        return ResponseEntity.ok(vedaService.suspenderVeda(id));
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<Veda> desactivarVeda(@PathVariable Long id) {
        return ResponseEntity.ok(vedaService.desactivarVeda(id));
    }
}