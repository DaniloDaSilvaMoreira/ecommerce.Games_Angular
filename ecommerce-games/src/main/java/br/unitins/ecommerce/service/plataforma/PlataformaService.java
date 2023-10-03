package br.unitins.ecommerce.service.plataforma;

import java.util.List;

import br.unitins.ecommerce.dto.plataforma.PlataformaDTO;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponseDTO;

public interface PlataformaService {
    
    List<PlataformaResponseDTO> getAll();
    
    PlataformaResponseDTO getById(Long id);

    PlataformaResponseDTO insert(PlataformaDTO plataformaDTO);

    PlataformaResponseDTO update(Long id, PlataformaDTO plataformaDTO);

    void delete(Long id);
}
