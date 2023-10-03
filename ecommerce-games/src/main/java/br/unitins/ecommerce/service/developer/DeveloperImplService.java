package br.unitins.ecommerce.service.developer;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import br.unitins.ecommerce.dto.developer.DeveloperDTO;
import br.unitins.ecommerce.dto.developer.DeveloperResponseDTO;
import br.unitins.ecommerce.model.produto.Developer;
import br.unitins.ecommerce.repository.DeveloperRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeveloperImplService implements DeveloperService {

    @Inject
    Validator validator;

    @Inject
    DeveloperRepository developerRepository;

    private DateTimeFormatter formatterGetAll = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private DateTimeFormatter formatterGetById = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<DeveloperResponseDTO> getAll() {

        Sort sort = Sort.by("id").ascending();
        
        return developerRepository.findAll(sort).stream().map(developer -> new DeveloperResponseDTO(developer, formatterGetAll)).toList();
    }

    @Override
    public DeveloperResponseDTO getById(Long id) {
        
        Developer developer = developerRepository.findById(id);

        if (developer == null)
            throw new NotFoundException("Não encontrado");

        return new DeveloperResponseDTO(developer, formatterGetById);
    }

    @Override
    @Transactional
    public DeveloperResponseDTO insert(DeveloperDTO developerDTO) {
        
        validar(developerDTO);

        Developer developer = new Developer();

        developer.setNome(developerDTO.nome());

        developer.setAnoFundacao(developerDTO.anoFundacao());

        developerRepository.persist(developer);

        return new DeveloperResponseDTO(developer);
    }

    @Override
    @Transactional
    public DeveloperResponseDTO update(Long id, DeveloperDTO developerDTO) {
        
        validar(developerDTO);

        Developer developer = developerRepository.findById(id);

        if (developer == null)
            throw new NotFoundException("Número fora das opções disponíveis");

        developer.setNome(developerDTO.nome());

        developer.setAnoFundacao(developerDTO.anoFundacao());

        return new DeveloperResponseDTO(developer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Developer developer = developerRepository.findById(id);

        if (developerRepository.isPersistent(developer))
            developerRepository.delete(developer);

        else
            throw new NotFoundException("Nenhum developer encontrado");
    }
    
    private void validar(DeveloperDTO developerDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<DeveloperDTO>> violations = validator.validate(developerDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
