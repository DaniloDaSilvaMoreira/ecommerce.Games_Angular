package br.unitins.ecommerce.service.game;

import java.util.List;
import java.util.Set;

import br.unitins.ecommerce.dto.GameDTO;
import br.unitins.ecommerce.model.produto.Game;
import br.unitins.ecommerce.repository.DeveloperRepository;
import br.unitins.ecommerce.repository.GameRepository;
import br.unitins.ecommerce.repository.GeneroRepository;
import br.unitins.ecommerce.repository.PlataformaRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GameImplService implements GameService {

    @Inject
    Validator validator;

    @Inject
    GameRepository gameRepository;

    @Inject
    DeveloperRepository developerRepository;

    @Inject
    GeneroRepository generoRepository;

    @Inject
    PlataformaRepository plataformaRepository;

    @Override
    public List<Game> getAll() {

        Sort sort = Sort.by("id").ascending();
        
        return gameRepository.findAll(sort).list();
    }

    @Override
    public Game getById(Long id) {
        
        Game game = gameRepository.findById(id);

        if (game == null)
            throw new NotFoundException("Não encontrado");

        return game;
    }

    @Override
    @Transactional
    public Game insert(GameDTO gameDTO) {
        
        validar(gameDTO);

        Game game = new Game();
        
        game.setNome(gameDTO.nome());

        game.setDescricao(gameDTO.descricao());

        game.setPreco(gameDTO.preco());

        game.setAnoLancamento(gameDTO.anoLancamento());

        game.setDeveloper(developerRepository.findById(gameDTO.developer()));

        for (Long genero : gameDTO.generos()) {
            
            game.plusGeneros(generoRepository.findById(genero));
        }

        for (Long plataforma : gameDTO.plataformas()) {
            
            game.plusPlataformas(plataformaRepository.findById(plataforma));
        }

        gameRepository.persist(game);

        return game;
    }

    @Override
    @Transactional
    public Game update(Long id, GameDTO gameDTO) {
        
        validar(gameDTO);

        Game game = gameRepository.findById(id);

        if (game == null)
            throw new NotFoundException("Número fora das opções disponíveis");
        
        game.setNome(gameDTO.nome());

        game.setDescricao(gameDTO.descricao());

        game.setPreco(gameDTO.preco());

        game.setAnoLancamento(gameDTO.anoLancamento());

        game.setDeveloper(developerRepository.findById(gameDTO.developer()));

        for (Long genero : gameDTO.generos()) {
            
            game.plusGeneros(generoRepository.findById(genero));
        }

        for (Long plataforma : gameDTO.plataformas()) {
            
            game.plusPlataformas(plataformaRepository.findById(plataforma));
        }
        
        return game;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Game game = gameRepository.findById(id);

        if (gameRepository.isPersistent(game))
            gameRepository.delete(game);

        else
            throw new NotFoundException("Nenhum game encontrado");
    }
 
    private void validar(GameDTO gameDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<GameDTO>> violations = validator.validate(gameDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
