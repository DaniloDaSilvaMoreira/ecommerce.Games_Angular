package br.unitins.ecommerce.service.fabricante;

import java.util.List;

import br.unitins.ecommerce.dto.fabricante.FabricanteDTO;
import br.unitins.ecommerce.dto.fabricante.FabricanteResponseDTO;

public interface FabricanteService {
    
    List<FabricanteResponseDTO> getAll();
    
    FabricanteResponseDTO getById(Long id);

    FabricanteResponseDTO insert(FabricanteDTO fabricanteDTO);

    FabricanteResponseDTO update(Long id, FabricanteDTO fabricanteDTO);

    void delete(Long id);
}
