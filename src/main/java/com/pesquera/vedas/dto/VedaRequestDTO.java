package com.pesquera.vedas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VedaRequestDTO {

    @NotBlank(message = "La especie es obligatoria")
    private String especie;

    @NotBlank(message = "La zona es obligatoria")
    private String zona;

    @NotBlank(message = "El motivo de la veda es obligatorio")
    private String motivoVeda;

    @NotBlank(message = "El organismo que decreta la veda es obligatorio")
    private String decretadaPor;
}