package br.unitins.ecommerce.dto.developer;

import java.time.format.DateTimeFormatter;

import br.unitins.ecommerce.model.produto.Developer;

public record DeveloperResponseDTO(Long id, String nome, String anoFundacao) {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public DeveloperResponseDTO (Developer developer, DateTimeFormatter formatter) {

        this(developer.getId(), developer.getNome(), developer.getAnoFundacao().format(formatter));
    }

    public DeveloperResponseDTO (Developer developer) {

        this(developer.getId(), developer.getNome(), developer.getAnoFundacao().format(formatter));
    }
}
