package br.unitins.ecommerce.repository;

import br.unitins.ecommerce.model.produto.Fabricante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FabricanteRepository implements PanacheRepository<Fabricante> {
    
}
