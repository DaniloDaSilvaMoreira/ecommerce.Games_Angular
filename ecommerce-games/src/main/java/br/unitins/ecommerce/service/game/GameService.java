package br.unitins.ecommerce.service.game;

import java.util.List;

import br.unitins.ecommerce.dto.GameDTO;
import br.unitins.ecommerce.model.produto.Game;

public interface GameService {
    
    List<Game> getAll();
    
    Game getById(Long id);

    Game insert(GameDTO gameDTO);

    Game update(Long id, GameDTO gameDTO);

    void delete(Long id);
}
