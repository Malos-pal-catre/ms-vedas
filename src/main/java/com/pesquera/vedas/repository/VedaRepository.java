package com.pesquera.vedas.repository;
import com.pesquera.vedas.model.Veda;
import com.pesquera.vedas.model.EstadoVeda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface VedaRepository extends JpaRepository<Veda, Long> {
    List<Veda> findByEstado(EstadoVeda estado);
    List<Veda> findByEspecie(String especie);
    List<Veda> findByZona(String zona);
    List<Veda> findByEspecieAndEstado(String especie, EstadoVeda estado);
    @Query("SELECT v FROM Veda v WHERE v.estado = com.pesquera.vedas.model.EstadoVeda.ACTIVA AND v.zona = :zona")
    List<Veda> findVedasActivasByZona(@Param("zona") String zona);
    @Query("SELECT v FROM Veda v WHERE v.especie = :especie ORDER BY v.fechaInicio DESC")
    List<Veda> findVedasByEspecieOrdenadas(@Param("especie") String especie);
    @Query(value = "SELECT * FROM vedas WHERE estado = :estado ORDER BY fecha_inicio ASC", nativeQuery = true)
    List<Veda> findTodasVedasActivas(@Param("estado") String estado);
}