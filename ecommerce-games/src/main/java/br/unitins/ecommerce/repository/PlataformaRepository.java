package br.unitins.ecommerce.repository;

import br.unitins.ecommerce.model.produto.Plataforma;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlataformaRepository implements PanacheRepository<Plataforma> {
    
}
