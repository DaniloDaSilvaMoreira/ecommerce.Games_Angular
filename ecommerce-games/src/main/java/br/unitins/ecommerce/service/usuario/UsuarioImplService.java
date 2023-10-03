package br.unitins.ecommerce.service.usuario;

import java.util.List;
import java.util.Set;

import br.unitins.ecommerce.dto.TelefoneDTO;
import br.unitins.ecommerce.dto.UsuarioDTO;
import br.unitins.ecommerce.model.usuario.Perfil;
import br.unitins.ecommerce.model.usuario.Telefone;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.repository.UsuarioRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioImplService implements UsuarioService {

    @Inject
    Validator validator;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAll() {

        Sort sort = Sort.by("id").ascending();
        
        return usuarioRepository.findAll(sort).list();
    }

    @Override
    public Usuario getById(Long id) {
        
        Usuario usuario = usuarioRepository.findById(id);

        if (usuario == null)
            throw new NotFoundException("Não encontrado");

        return usuario;
    }

    @Override
    @Transactional
    public Usuario insert(UsuarioDTO usuarioDto) {
        
        validar(usuarioDto);

        Usuario entity = new Usuario();

        entity.setNome(usuarioDto.nome());

        entity.setCpf(usuarioDto.cpf());

        entity.setEmail(usuarioDto.email());

        entity.setLogin(usuarioDto.login());

        entity.setSenha(usuarioDto.senha());

        entity.setPerfil(Perfil.valueOf(usuarioDto.perfil()));

        for (TelefoneDTO telefoneDTO : usuarioDto.telefones()) {
            
            entity.getTelefones().add(new Telefone(telefoneDTO));
        }

        usuarioRepository.persist(entity);

        return entity;
    }

    @Override
    @Transactional
    public Usuario update(Long id, UsuarioDTO usuarioDto) {
        
        validar(usuarioDto);

        Usuario entity = usuarioRepository.findById(id);

        if (entity == null)
            throw new NotFoundException("Número fora das opções disponíveis");

        entity.setNome(usuarioDto.nome());

        entity.setCpf(usuarioDto.cpf());

        entity.setEmail(usuarioDto.email());

        entity.setLogin(usuarioDto.login());

        entity.setSenha(usuarioDto.senha());

        entity.setPerfil(Perfil.valueOf(usuarioDto.perfil()));

        // for (Telefone telefone : entity.getTelefones()) {
            
        //     entity.getTelefones().remove(telefone);
        // }

        entity.getTelefones().clear();

        for (TelefoneDTO telefoneDTO : usuarioDto.telefones()) {
            
            entity.getTelefones().add(new Telefone(telefoneDTO));
        }

        return entity;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Usuario usuario = usuarioRepository.findById(id);

        if (usuarioRepository.isPersistent(usuario))
            usuarioRepository.delete(usuario);

        else
            throw new NotFoundException("Nenhum usuario encontrado");
    }
    
    private void validar(UsuarioDTO usuarioDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(usuarioDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
