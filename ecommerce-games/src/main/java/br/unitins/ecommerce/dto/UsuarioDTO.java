package br.unitins.ecommerce.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
    @NotBlank(message = "O campo nome não pode estar nulo")
    String nome,

    @NotBlank(message = "O campo CPF não pode estar nulo")
    String cpf,

    @NotBlank(message = "O campo email não pode estar nulo")
    String email,

    @NotBlank(message = "O campo login não pode estar nulo")
    String login,

    @NotBlank(message = "O campo senha não pode estar nulo")
    String senha,

    @NotNull
    Integer perfil,

    List<TelefoneDTO> telefones
) {
    
}
