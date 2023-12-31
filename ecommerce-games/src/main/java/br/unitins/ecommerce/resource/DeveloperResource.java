package br.unitins.ecommerce.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.developer.DeveloperDTO;
import br.unitins.ecommerce.dto.developer.DeveloperResponseDTO;
import br.unitins.ecommerce.service.developer.DeveloperService;
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

@Path("/developers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeveloperResource {
    
    @Inject
    DeveloperService developerService;

    private static final Logger LOG = Logger.getLogger(DeveloperResource.class);

    @GET
    public List<DeveloperResponseDTO> getAll() {
        LOG.infof("Buscando todos os developers");
        LOG.debug("ERRO DE DEBUG.");
        return developerService.getAll();
    }

    @GET
    @Path("/{id}")
    public DeveloperResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.infof("Buscando developers por ID. ", id);
        LOG.debug("ERRO DE DEBUG.");
        return developerService.getById(id);
    }

    @POST
    public Response insert(DeveloperDTO developerDTO) {
        LOG.infof("Inserindo um developer: %s", developerDTO.nome());
        Result result = null;
        try {

            return Response
                    .status(Status.CREATED) // 201
                    .entity(developerService.insert(developerDTO))
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um developer.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), false);

        }
        return Response
                .status(Status.NOT_FOUND)
                .entity(result)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, DeveloperDTO developerDTO) {
        Result result = null;
        try {
            developerService.update(id, developerDTO);
            LOG.infof("Developer (%d) atualizado com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.errorf("Erro ao atualizar um developer. ", id, e);
            LOG.debug(e.getMessage());

            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), false);

        }
        return Response
                .status(Status.NOT_FOUND)
                .entity(result)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException, NotFoundException {
        try {
            developerService.delete(id);
            LOG.infof("developer excluído com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT)
                    .build();
        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar um developer: parâmetros inválidos.", e);
            throw e;
        }
    }
}
