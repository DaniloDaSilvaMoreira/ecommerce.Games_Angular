package br.unitins.ecommerce.service.developer;

import java.util.List;

import br.unitins.ecommerce.dto.developer.DeveloperDTO;
import br.unitins.ecommerce.dto.developer.DeveloperResponseDTO;

public interface DeveloperService {
    
    List<DeveloperResponseDTO> getAll();
    
    DeveloperResponseDTO getById(Long id);

    DeveloperResponseDTO insert(DeveloperDTO developerDTO);

    DeveloperResponseDTO update(Long id, DeveloperDTO developerDTO);

    void delete(Long id);
}
