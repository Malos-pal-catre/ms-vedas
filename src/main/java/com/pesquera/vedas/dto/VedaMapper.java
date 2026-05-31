package com.pesquera.vedas.dto;
import com.pesquera.vedas.model.Veda;
public class VedaMapper {
    public static VedaResponseDTO toDTO(Veda veda) {
        VedaResponseDTO dto = new VedaResponseDTO();
        dto.setId(veda.getId());
        dto.setEspecie(veda.getEspecie());
        dto.setZona(veda.getZona());
        dto.setEstado(veda.getEstado().name());
        dto.setFechaInicio(veda.getFechaInicio());
        dto.setFechaFin(veda.getFechaFin());
        dto.setMotivoVeda(veda.getMotivoVeda());
        dto.setDecretadaPor(veda.getDecretadaPor());
        return dto;
    }
    public static Veda toEntity(VedaRequestDTO dto) {
        Veda veda = new Veda();
        veda.setEspecie(dto.getEspecie());
        veda.setZona(dto.getZona());
        veda.setMotivoVeda(dto.getMotivoVeda());
        veda.setDecretadaPor(dto.getDecretadaPor());
        return veda;
    }
}