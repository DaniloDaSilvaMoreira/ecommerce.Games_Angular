package br.unitins.ecommerce.service.usuario;

import java.util.List;

import br.unitins.ecommerce.dto.UsuarioDTO;
import br.unitins.ecommerce.model.usuario.Usuario;

public interface UsuarioService {
    
    List<Usuario> getAll();

    Usuario getById(Long id);

    Usuario insert(UsuarioDTO usuarioDto);

    Usuario update(Long id, UsuarioDTO usuarioDto);

    void delete(Long id);
}
