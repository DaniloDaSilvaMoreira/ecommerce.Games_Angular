package br.unitins.ecommerce.service.genero;

import java.util.List;
import java.util.Set;

import br.unitins.ecommerce.dto.GeneroDTO;
import br.unitins.ecommerce.model.produto.Genero;
import br.unitins.ecommerce.repository.GeneroRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GeneroImplService implements GeneroService {

    @Inject
    Validator validator;

    @Inject
    GeneroRepository generoRepository;

    @Override
    public List<Genero> getAll() {

        Sort sort = Sort.by("id").ascending();
        
        return generoRepository.findAll(sort).list();
    }

    @Override
    public Genero getById(Long id) {
        
        Genero genero = generoRepository.findById(id);

        if (genero == null)
            throw new NotFoundException("Não encontrado");

        return genero;
    }

    @Override
    @Transactional
    public Genero insert(GeneroDTO generoDTO) {
        
        validar(generoDTO);

        Genero genero = new Genero();

        genero.setNome(generoDTO.nome());

        generoRepository.persist(genero);

        return genero;
    }

    @Override
    @Transactional
    public Genero update(Long id, GeneroDTO generoDTO) {
        
        validar(generoDTO);

        Genero genero = generoRepository.findById(id);

        if (genero == null)
            throw new NotFoundException("Número fora das opções disponíveis");

        genero.setNome(generoDTO.nome());

        return genero;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Genero genero = generoRepository.findById(id);

        if (generoRepository.isPersistent(genero))
            generoRepository.delete(genero);

        else
            throw new NotFoundException("Nenhum genero encontrado");
    }
    
    private void validar(GeneroDTO generoDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<GeneroDTO>> violations = validator.validate(generoDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
