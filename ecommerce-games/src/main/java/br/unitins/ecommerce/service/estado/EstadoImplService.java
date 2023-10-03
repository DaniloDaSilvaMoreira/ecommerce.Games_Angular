package br.unitins.ecommerce.service.estado;

import java.util.List;
import java.util.Set;

import br.unitins.ecommerce.dto.estado.EstadoDTO;
import br.unitins.ecommerce.dto.estado.EstadoResponseDTO;
import br.unitins.ecommerce.model.endereco.Estado;
import br.unitins.ecommerce.repository.EstadoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstadoImplService implements EstadoService {

    @Inject
    Validator validator;

    @Inject
    EstadoRepository estadoRepository;

    @Override
    public List<EstadoResponseDTO> getAll() {

        Sort sort = Sort.by("id").ascending();
        
        return estadoRepository.findAll(sort).stream().map(EstadoResponseDTO::new).toList();
    }

    @Override
    public Estado getById(Long id) {
        
        Estado estado = estadoRepository.findById(id);

        if (estado == null)
            throw new NotFoundException("Não encontrado");

        return estado;
    }

    @Override
    @Transactional
    public Estado insert(EstadoDTO estadoDto) {
        
        validar(estadoDto);

        Estado estado = new Estado();

        estado.setNome(estadoDto.nome());

        estado.setSigla(estadoDto.sigla());

        estadoRepository.persist(estado);

        return estado;
    }

    @Override
    @Transactional
    public Estado update(Long id, EstadoDTO estadoDto) {
        
        validar(estadoDto);

        Estado estado = estadoRepository.findById(id);

        if (estado == null)
            throw new NotFoundException("Número fora das opções disponíveis");

        estado.setNome(estadoDto.nome());

        estado.setSigla(estadoDto.sigla());

        return estado;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Estado estado = estadoRepository.findById(id);

        if (estadoRepository.isPersistent(estado))
            estadoRepository.delete(estado);

        else
            throw new NotFoundException("Nenhum estado encontrado");
    }
    
    private void validar(EstadoDTO estadoDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<EstadoDTO>> violations = validator.validate(estadoDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
