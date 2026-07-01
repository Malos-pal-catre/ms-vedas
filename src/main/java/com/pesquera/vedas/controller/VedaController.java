package com.pesquera.vedas.controller;

import com.pesquera.vedas.dto.VedaMapper;
import com.pesquera.vedas.dto.VedaRequestDTO;
import com.pesquera.vedas.dto.VedaResponseDTO;
import com.pesquera.vedas.model.Veda;
import com.pesquera.vedas.service.VedaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vedas")
@RequiredArgsConstructor
@Tag(name = "Vedas", description = "Gestión de vedas Sernapesca - Caleta Lo Abarca")
public class VedaController {

    private final VedaService vedaService;

    @PostMapping
    @Operation(
            summary = "Crear una nueva veda",
            description = "Decreta una veda para una especie en una zona determinada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Veda creada correctamente",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "VedaCreada",
                                    value = "{\"id\":1,\"especie\":\"Loco\",\"zona\":\"Lo Abarca\",\"estado\":\"ACTIVA\",\"fechaInicio\":\"2026-06-30\",\"fechaFin\":null,\"motivoVeda\":\"Periodo de reproducción\",\"decretadaPor\":\"Sernapesca\"}"
                            ))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ErrorValidacion",
                                    value = "{\"especie\":\"La especie es obligatoria\"}"
                            )))
    })
    public ResponseEntity<VedaResponseDTO> crearVeda(
            @org.springframework.web.bind.annotation.RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la veda a crear",
                    required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = VedaRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "NuevaVeda",
                                    value = "{\"especie\":\"Loco\",\"zona\":\"Lo Abarca\",\"motivoVeda\":\"Periodo de reproducción\",\"decretadaPor\":\"Sernapesca\"}"
                            )))
            @Valid VedaRequestDTO dto) {
        Veda veda = VedaMapper.toEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(VedaMapper.toDTO(vedaService.crearVeda(veda)));
    }

    @GetMapping
    @Operation(
            summary = "Listar todas las vedas",
            description = "Retorna el listado completo de vedas registradas, sin importar su estado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "ListadoVedas",
                                    value = "[{\"id\":1,\"especie\":\"Loco\",\"zona\":\"Lo Abarca\",\"estado\":\"ACTIVA\",\"fechaInicio\":\"2026-06-30\",\"fechaFin\":null,\"motivoVeda\":\"Periodo de reproducción\",\"decretadaPor\":\"Sernapesca\"}]"
                            )))
    })
    public ResponseEntity<List<VedaResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(vedaService.obtenerTodas().stream().map(VedaMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar veda por ID",
            description = "Retorna los datos de una veda específica según su identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veda encontrada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "VedaEncontrada",
                                    value = "{\"id\":1,\"especie\":\"Loco\",\"zona\":\"Lo Abarca\",\"estado\":\"ACTIVA\",\"fechaInicio\":\"2026-06-30\",\"fechaFin\":null,\"motivoVeda\":\"Periodo de reproducción\",\"decretadaPor\":\"Sernapesca\"}"
                            ))),
            @ApiResponse(responseCode = "404", description = "Veda no encontrada")
    })
    public ResponseEntity<VedaResponseDTO> obtenerPorId(
            @Parameter(description = "ID de la veda", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(VedaMapper.toDTO(vedaService.obtenerPorId(id)));
    }

    @GetMapping("/activas")
    @Operation(
            summary = "Listar vedas activas",
            description = "Retorna únicamente las vedas que se encuentran vigentes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de vedas activas",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "VedasActivas",
                                    value = "[{\"id\":1,\"especie\":\"Loco\",\"zona\":\"Lo Abarca\",\"estado\":\"ACTIVA\",\"fechaInicio\":\"2026-06-30\",\"fechaFin\":null,\"motivoVeda\":\"Periodo de reproducción\",\"decretadaPor\":\"Sernapesca\"}]"
                            )))
    })
    public ResponseEntity<List<VedaResponseDTO>> obtenerActivas() {
        return ResponseEntity.ok(vedaService.obtenerActivas().stream().map(VedaMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/especie/{especie}")
    @Operation(
            summary = "Listar vedas por especie",
            description = "Retorna todas las vedas (vigentes o no) asociadas a una especie."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de vedas por especie",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "VedasPorEspecie",
                                    value = "[{\"id\":1,\"especie\":\"Loco\",\"zona\":\"Lo Abarca\",\"estado\":\"ACTIVA\",\"fechaInicio\":\"2026-06-30\",\"fechaFin\":null,\"motivoVeda\":\"Periodo de reproducción\",\"decretadaPor\":\"Sernapesca\"}]"
                            )))
    })
    public ResponseEntity<List<VedaResponseDTO>> obtenerPorEspecie(
            @Parameter(description = "Nombre de la especie", example = "Loco")
            @PathVariable String especie) {
        return ResponseEntity.ok(vedaService.obtenerPorEspecie(especie).stream().map(VedaMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/verificar/{especie}")
    @Operation(
            summary = "Verificar si una especie está en veda",
            description = "Valida si la especie indicada tiene actualmente una veda activa. Usado por otros microservicios para validar antes de registrar una captura."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado de la verificación",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "EspecieEnVeda",
                                    value = "true"
                            )))
    })
    public ResponseEntity<Boolean> verificarVeda(
            @Parameter(description = "Nombre de la especie a verificar", example = "Loco")
            @PathVariable String especie) {
        return ResponseEntity.ok(vedaService.especieEnVeda(especie));
    }

    @PutMapping("/{id}/suspender")
    @Operation(
            summary = "Suspender una veda",
            description = "Cambia el estado de una veda a suspendida temporalmente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veda suspendida correctamente",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "VedaSuspendida",
                                    value = "{\"id\":1,\"especie\":\"Loco\",\"zona\":\"Lo Abarca\",\"estado\":\"SUSPENDIDA\",\"fechaInicio\":\"2026-06-30\",\"fechaFin\":null,\"motivoVeda\":\"Periodo de reproducción\",\"decretadaPor\":\"Sernapesca\"}"
                            ))),
            @ApiResponse(responseCode = "404", description = "Veda no encontrada")
    })
    public ResponseEntity<VedaResponseDTO> suspenderVeda(
            @Parameter(description = "ID de la veda a suspender", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(VedaMapper.toDTO(vedaService.suspenderVeda(id)));
    }

    @PutMapping("/{id}/desactivar")
    @Operation(
            summary = "Desactivar una veda",
            description = "Cambia el estado de una veda a inactiva (finalizada)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Veda desactivada correctamente",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "VedaDesactivada",
                                    value = "{\"id\":1,\"especie\":\"Loco\",\"zona\":\"Lo Abarca\",\"estado\":\"INACTIVA\",\"fechaInicio\":\"2026-06-30\",\"fechaFin\":\"2026-06-30\",\"motivoVeda\":\"Periodo de reproducción\",\"decretadaPor\":\"Sernapesca\"}"
                            ))),
            @ApiResponse(responseCode = "404", description = "Veda no encontrada")
    })
    public ResponseEntity<VedaResponseDTO> desactivarVeda(
            @Parameter(description = "ID de la veda a desactivar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(VedaMapper.toDTO(vedaService.desactivarVeda(id)));
    }
}