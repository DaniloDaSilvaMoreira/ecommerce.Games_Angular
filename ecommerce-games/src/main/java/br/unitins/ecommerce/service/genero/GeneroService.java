package br.unitins.ecommerce.service.genero;

import java.util.List;

import br.unitins.ecommerce.dto.GeneroDTO;
import br.unitins.ecommerce.model.produto.Genero;

public interface GeneroService {
    
    List<Genero> getAll();
    
    Genero getById(Long id);

    Genero insert(GeneroDTO generoDTO);

    Genero update(Long id, GeneroDTO generoDTO);

    void delete(Long id);
}
