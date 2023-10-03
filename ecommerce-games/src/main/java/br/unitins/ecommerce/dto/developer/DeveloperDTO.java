package br.unitins.ecommerce.dto.developer;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeveloperDTO(

    @NotBlank(message = "Campo nome não pode estar vazio")
    String nome,

    @NotNull(message = "Campo anoFundacao não pode estar vazio")
    LocalDate anoFundacao
) {
    
}
