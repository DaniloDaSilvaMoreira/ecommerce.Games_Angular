package br.unitins.ecommerce.repository;

import br.unitins.ecommerce.model.produto.Genero;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GeneroRepository implements PanacheRepository<Genero> {
    
}
