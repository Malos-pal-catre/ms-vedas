package com.pesquera.vedas.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class VedaResponseDTO {
    private Long id;
    private String especie;
    private String zona;
    private String estado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String motivoVeda;
    private String decretadaPor;
}