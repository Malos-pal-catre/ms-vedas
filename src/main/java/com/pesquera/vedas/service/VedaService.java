package com.pesquera.vedas.service;

import com.pesquera.vedas.model.Veda;
import com.pesquera.vedas.model.EstadoVeda;
import com.pesquera.vedas.repository.VedaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VedaService {

    private final VedaRepository vedaRepository;

    public Veda crearVeda(Veda veda) {
        veda.setEstado(EstadoVeda.ACTIVA);
        veda.setFechaInicio(LocalDate.now());
        return vedaRepository.save(veda);
    }

    public List<Veda> obtenerTodas() {
        return vedaRepository.findAll();
    }

    public Veda obtenerPorId(Long id) {
        return vedaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veda no encontrada con id: " + id));
    }

    public List<Veda> obtenerActivas() {
        return vedaRepository.findByEstado(EstadoVeda.ACTIVA);
    }

    public List<Veda> obtenerPorEspecie(String especie) {
        return vedaRepository.findByEspecie(especie);
    }

    public boolean especieEnVeda(String especie) {
        return !vedaRepository.findByEspecieAndEstado(especie, EstadoVeda.ACTIVA).isEmpty();
    }

    public Veda suspenderVeda(Long id) {
        Veda veda = obtenerPorId(id);
        veda.setEstado(EstadoVeda.SUSPENDIDA);
        veda.setFechaFin(LocalDate.now());
        return vedaRepository.save(veda);
    }

    public Veda desactivarVeda(Long id) {
        Veda veda = obtenerPorId(id);
        veda.setEstado(EstadoVeda.INACTIVA);
        veda.setFechaFin(LocalDate.now());
        return vedaRepository.save(veda);
    }
}