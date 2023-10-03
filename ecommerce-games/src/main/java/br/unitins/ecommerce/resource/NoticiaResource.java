package br.unitins.ecommerce.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.NoticiaDTO;
import br.unitins.ecommerce.model.noticia.Noticia;
import br.unitins.ecommerce.service.noticia.NoticiaService;
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

@Path("/noticias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoticiaResource {
    
    @Inject
    NoticiaService noticiaService;

    private static final Logger LOG = Logger.getLogger(NoticiaResource.class);

    @GET
    public List<Noticia> getAll() {
        LOG.info("Buscando todos as Noticias.");
        LOG.debug("ERRO DE DEBUG.");
        return noticiaService.getAll();
    }

    @GET
    @Path("/{id}")
    public Noticia getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.info("Buscando Noticia por ID: " + id);
        LOG.debug("ERRO DE DEBUG.");
        return noticiaService.getById(id);
    }

    @POST
    public Response insert(NoticiaDTO noticiaDTO) {
        LOG.infof("Inserindo uma Noticia: %s", noticiaDTO.titulo());

        Result result = null;

        try {
            Noticia noticia = noticiaService.insert(noticiaDTO);

            LOG.infof("noticia (%d) criado com sucesso.", noticia.getId());

            return Response.status(Status.CREATED).entity(noticia).build();

        } catch (ConstraintViolationException e) {

            LOG.error("Erro ao incluir uma Noticia.");

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
    public Response update(@PathParam("id") Long id, NoticiaDTO noticiaDTO) {
        Result result = null;
        
        try {
            noticiaService.update(id, noticiaDTO);
            LOG.infof("Noticia (%d) atualizado com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro de validação ao atualizar a Noticia.", e);
            LOG.debug(e.getMessage());

            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro ao atualizar a Noticia " + id + ".", e);
            result = new Result(e.getMessage(), false);
    
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException, NotFoundException {

        try {
            noticiaService.delete(id);
            LOG.infof("Noticia (%d) excluído com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT)
                    .build();
        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar Noticia: parâmetros inválidos.", e);
            throw e;
        } catch (NotFoundException e) {
            LOG.errorf("Noticia (%d) não encontrado.", id);
            throw e;
        }
    }
}
