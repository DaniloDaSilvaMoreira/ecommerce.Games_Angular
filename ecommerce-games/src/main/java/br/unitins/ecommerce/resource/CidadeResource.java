package br.unitins.ecommerce.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.cidade.CidadeDTO;
import br.unitins.ecommerce.dto.cidade.CidadeResponseDTO;
import br.unitins.ecommerce.service.cidade.CidadeService;
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

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CidadeResource {
    
    @Inject
    CidadeService cidadeService;

    private static final Logger LOG = Logger.getLogger(CidadeResource.class);

    @GET
    public List<CidadeResponseDTO> getAll() {
        LOG.info("Buscando todos as cidades.");
        LOG.debug("ERRO DE DEBUG.");
        return cidadeService.getAll();
    }

    @GET
    @Path("/{id}")
    public CidadeResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.info("Buscando cidade por ID: " + id);
        LOG.debug("ERRO DE DEBUG.");
        return cidadeService.getById(id);
    }

    @POST
    public Response insert(CidadeDTO cidadeDTO) {
        LOG.infof("Inserindo uma cidade: %s", cidadeDTO.nome());

        Result result = null;

        try {
            CidadeResponseDTO cidadeResponseDTO = cidadeService.insert(cidadeDTO);

            LOG.infof("Cidade (%d) criado com sucesso.", cidadeResponseDTO.id());

            return Response.status(Status.CREATED).entity(cidadeResponseDTO).build();

        } catch (ConstraintViolationException e) {

            LOG.error("Erro ao incluir uma cidade.");

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
    public Response update(@PathParam("id") Long id, CidadeDTO cidadeDTO) {
        Result result = null;
        
        try {
            cidadeService.update(id, cidadeDTO);
            LOG.infof("Cidade (%d) atualizado com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro de validação ao atualizar a cidade.", e);
            LOG.debug(e.getMessage());

            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro ao atualizar a cidade " + id + ".", e);
            result = new Result(e.getMessage(), false);
    
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException, NotFoundException {

        try {
            cidadeService.delete(id);
            LOG.infof("Cidade (%d) excluído com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT)
                    .build();
        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar cidade: parâmetros inválidos.", e);
            throw e;
        } catch (NotFoundException e) {
            LOG.errorf("Cidade (%d) não encontrado.", id);
            throw e;
        }
    }
}
