package br.unitins.ecommerce.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.GameDTO;
import br.unitins.ecommerce.model.produto.Game;
import br.unitins.ecommerce.service.game.GameService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {
    
    @Inject
    GameService gameService;

    private static final Logger LOG = Logger.getLogger(GameResource.class);

    @GET
    public List<Game> getAll() {
        LOG.info("Buscando todos as Games.");
        LOG.debug("ERRO DE DEBUG.");
        return gameService.getAll();
    }

    @GET
    @Path("/{id}")
    public Game getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.info("Buscando Game por ID: " + id);
        LOG.debug("ERRO DE DEBUG.");
        return gameService.getById(id);
    }

    @POST
    public Response insert(GameDTO gameDTO) {
        LOG.infof("Inserindo uma Game: %s", gameDTO.nome());

        Result result = null;

        try {
            Game game = gameService.insert(gameDTO);

            LOG.infof("Game (%d) criado com sucesso.", game.getId());

            return Response.status(Status.CREATED).entity(game).build();

        } catch (ConstraintViolationException e) {

            LOG.error("Erro ao incluir uma Game.");

            LOG.debug(e.getMessage());

            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());

            result = new Result(e.getMessage(), false);
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, GameDTO gameDTO) {
        Result result = null;
        
        try {
            gameService.update(id, gameDTO);
            LOG.infof("Game (%d) atualizado com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro de validação ao atualizar a Game.", e);
            LOG.debug(e.getMessage());

            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro ao atualizar a Game " + id + ".", e);
            result = new Result(e.getMessage(), false);
    
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException, NotFoundException {

        try {
            gameService.delete(id);
            LOG.infof("Game (%d) excluído com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT)
                    .build();
        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar Game: parâmetros inválidos.", e);
            throw e;
        } catch (NotFoundException e) {
            LOG.errorf("Game (%d) não encontrado.", id);
            throw e;
        }
    }
}
