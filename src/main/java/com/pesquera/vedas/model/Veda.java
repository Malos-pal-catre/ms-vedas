package com.pesquera.vedas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vedas")
public class Veda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false)
    private String zona;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVeda estado;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Column(nullable = false)
    private String motivoVeda;

    @Column(nullable = false)
    private String decretadaPor;
}