package br.unitins.ecommerce.service.cidade;

import java.util.List;

import br.unitins.ecommerce.dto.cidade.CidadeDTO;
import br.unitins.ecommerce.dto.cidade.CidadeResponseDTO;

public interface CidadeService {
    
    List<CidadeResponseDTO> getAll();
    
    CidadeResponseDTO getById(Long id);

    CidadeResponseDTO insert(CidadeDTO cidadeDTO);

    CidadeResponseDTO update(Long id, CidadeDTO cidadeDTO);

    void delete(Long id);
}
