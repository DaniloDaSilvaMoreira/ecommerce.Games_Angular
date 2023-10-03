package br.unitins.ecommerce.service.estado;

import java.util.List;

import br.unitins.ecommerce.dto.estado.EstadoDTO;
import br.unitins.ecommerce.dto.estado.EstadoResponseDTO;
import br.unitins.ecommerce.model.endereco.Estado;

public interface EstadoService {
    
    List<EstadoResponseDTO> getAll();
    
    Estado getById(Long id);

    Estado insert(EstadoDTO estadoDto);

    Estado update(Long id, EstadoDTO estadoDto);

    void delete(Long id);
}
