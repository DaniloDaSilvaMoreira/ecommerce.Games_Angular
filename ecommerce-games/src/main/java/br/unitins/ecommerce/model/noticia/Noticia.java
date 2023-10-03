package br.unitins.ecommerce.model.noticia;

import java.time.LocalDate;

import br.unitins.ecommerce.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity
public class Noticia extends DefaultEntity {
    
    @Column(nullable = false)
    private String titulo;

    @Lob
    @Column(nullable = false)
    private String conteudo;

    @Column(nullable = false)
    private LocalDate dataPublicacao;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private TopicoPrincipal topicoPrincipal;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public TopicoPrincipal getTopicoPrincipal() {
        return topicoPrincipal;
    }

    public void setTopicoPrincipal(TopicoPrincipal topicoPrincipal) {
        this.topicoPrincipal = topicoPrincipal;
    }
}
