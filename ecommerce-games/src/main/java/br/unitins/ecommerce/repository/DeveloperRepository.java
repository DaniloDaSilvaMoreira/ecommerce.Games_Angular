package br.unitins.ecommerce.repository;

import br.unitins.ecommerce.model.produto.Developer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeveloperRepository implements PanacheRepository<Developer> {
    
}
