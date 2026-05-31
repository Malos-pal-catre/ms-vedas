package com.pesquera.vedas.controller;
import com.pesquera.vedas.dto.VedaMapper;
import com.pesquera.vedas.dto.VedaRequestDTO;
import com.pesquera.vedas.dto.VedaResponseDTO;
import com.pesquera.vedas.model.Veda;
import com.pesquera.vedas.service.VedaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/vedas")
@RequiredArgsConstructor
public class VedaController {
    private final VedaService vedaService;
    @PostMapping
    public ResponseEntity<VedaResponseDTO> crearVeda(@Valid @RequestBody VedaRequestDTO dto) {
        Veda veda = VedaMapper.toEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(VedaMapper.toDTO(vedaService.crearVeda(veda)));
    }
    @GetMapping
    public ResponseEntity<List<VedaResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(vedaService.obtenerTodas().stream().map(VedaMapper::toDTO).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<VedaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(VedaMapper.toDTO(vedaService.obtenerPorId(id)));
    }
    @GetMapping("/activas")
    public ResponseEntity<List<VedaResponseDTO>> obtenerActivas() {
        return ResponseEntity.ok(vedaService.obtenerActivas().stream().map(VedaMapper::toDTO).collect(Collectors.toList()));
    }
    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<VedaResponseDTO>> obtenerPorEspecie(@PathVariable String especie) {
        return ResponseEntity.ok(vedaService.obtenerPorEspecie(especie).stream().map(VedaMapper::toDTO).collect(Collectors.toList()));
    }
    @GetMapping("/verificar/{especie}")
    public ResponseEntity<Boolean> verificarVeda(@PathVariable String especie) {
        return ResponseEntity.ok(vedaService.especieEnVeda(especie));
    }
    @PutMapping("/{id}/suspender")
    public ResponseEntity<VedaResponseDTO> suspenderVeda(@PathVariable Long id) {
        return ResponseEntity.ok(VedaMapper.toDTO(vedaService.suspenderVeda(id)));
    }
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<VedaResponseDTO> desactivarVeda(@PathVariable Long id) {
        return ResponseEntity.ok(VedaMapper.toDTO(vedaService.desactivarVeda(id)));
    }
}