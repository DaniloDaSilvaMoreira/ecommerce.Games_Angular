package br.unitins.ecommerce.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NoticiaDTO(

    @NotBlank(message = "O campo titulo não pode estar nulo")
    String titulo,

    @NotBlank(message = "O campo conteudo não pode estar nulo")
    String conteudo,

    @NotBlank(message = "O campo autor não pode estar nulo")
    String autor,

    @NotNull(message = "Campo dataPublicacao não pode estar vazio")
    LocalDate dataPublicacao,

    @NotNull
    Integer topicoPrincipal
) {
    
}
