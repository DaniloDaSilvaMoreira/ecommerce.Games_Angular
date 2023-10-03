package br.unitins.ecommerce.model.produto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Game extends Produto {
    
    @Column(nullable = false)
    private LocalDate anoLancamento;

    @ManyToOne
    @JoinColumn(name = "id_developer", nullable = false)
    private Developer developer;

    @ManyToMany
    @JoinTable(name = "generos_game",
                joinColumns = @JoinColumn(name = "id_game"),
                inverseJoinColumns = @JoinColumn(name = "id_genero"))
    // Criando uma tabela auxiliar
    private List<Genero> generos;
    
    @ManyToMany
    @JoinTable(name = "plataformas_game",
                joinColumns = @JoinColumn(name = "id_game"),
                inverseJoinColumns = @JoinColumn(name = "id_plataforma"))
    // Criando uma tabela auxiliar
    private List<Plataforma> plataformas;

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public void plusGeneros(Genero genero) {
        
        if (generos == null) {

            generos = new ArrayList<>();
        }
        
        this.generos.add(genero);
    }

    public List<Plataforma> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    public void plusPlataformas(Plataforma plataforma) {
        
        if (plataformas == null) {

            plataformas = new ArrayList<>();
        }
        
        this.plataformas.add(plataforma);
    }
}
