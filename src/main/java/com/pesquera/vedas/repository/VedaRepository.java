package com.pesquera.vedas.repository;

import com.pesquera.vedas.model.Veda;
import com.pesquera.vedas.model.EstadoVeda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VedaRepository extends JpaRepository<Veda, Long> {

    List<Veda> findByEstado(EstadoVeda estado);

    List<Veda> findByEspecie(String especie);

    List<Veda> findByZona(String zona);

    List<Veda> findByEspecieAndEstado(String especie, EstadoVeda estado);
}