package br.unitins.ecommerce.service.noticia;

import java.util.List;
import java.util.Set;

import br.unitins.ecommerce.dto.NoticiaDTO;
import br.unitins.ecommerce.model.noticia.Noticia;
import br.unitins.ecommerce.model.noticia.TopicoPrincipal;
import br.unitins.ecommerce.repository.NoticiaRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class NoticiaImplService implements NoticiaService {

    @Inject
    Validator validator;

    @Inject
    NoticiaRepository noticiaRepository;

    @Override
    public List<Noticia> getAll() {
        
        Sort sort = Sort.by("id").ascending();

        return noticiaRepository.findAll(sort).list();
    }

    @Override
    public Noticia getById(Long id) {
        
        Noticia noticia = noticiaRepository.findById(id);

        if (noticia == null)
            throw new NotFoundException("Não encontrado");

        return noticia;
    }

    @Override
    @Transactional
    public Noticia insert(NoticiaDTO noticiaDTO) {
        
        validar(noticiaDTO);

        Noticia noticia = new Noticia();

        noticia.setTitulo(noticiaDTO.titulo());

        noticia.setConteudo(noticiaDTO.conteudo());

        noticia.setAutor(noticiaDTO.autor());

        noticia.setDataPublicacao(noticiaDTO.dataPublicacao());

        noticia.setTopicoPrincipal(TopicoPrincipal.valueOf(noticiaDTO.topicoPrincipal()));

        noticiaRepository.persist(noticia);

        return noticia;
    }

    @Override
    @Transactional
    public Noticia update(Long id, NoticiaDTO noticiaDTO) {
        
        validar(noticiaDTO);

        Noticia noticia = noticiaRepository.findById(id);

        if (noticia == null)
            throw new NotFoundException("Número fora das opções disponíveis");

        noticia.setTitulo(noticiaDTO.titulo());

        noticia.setConteudo(noticiaDTO.conteudo());

        noticia.setAutor(noticiaDTO.autor());

        noticia.setDataPublicacao(noticiaDTO.dataPublicacao());

        noticia.setTopicoPrincipal(TopicoPrincipal.valueOf(noticiaDTO.topicoPrincipal()));

        return noticia;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Noticia noticia = noticiaRepository.findById(id);

        if (noticiaRepository.isPersistent(noticia))
            noticiaRepository.delete(noticia);

        else
            throw new NotFoundException("Nenhum noticia encontrado");
    }
    
    private void validar(NoticiaDTO noticiaDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<NoticiaDTO>> violations = validator.validate(noticiaDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}
