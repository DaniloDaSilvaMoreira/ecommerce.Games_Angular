package br.unitins.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GameDTO(
    
    @NotBlank(message = "O campo nome não pode estar nulo")
    String nome,

    String descricao,

    @NotNull(message = "O campo preco não pode estar nulo")
    Double preco,

    @NotNull(message = "O campo anoLancamento não pode estar nulo")
    LocalDate anoLancamento,

    @NotNull
    Long developer,

    List<Long> generos,

    List<Long> plataformas
) {
    
}
