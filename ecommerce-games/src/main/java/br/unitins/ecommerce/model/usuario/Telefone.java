package br.unitins.ecommerce.model.usuario;

import br.unitins.ecommerce.dto.TelefoneDTO;
import br.unitins.ecommerce.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Telefone extends DefaultEntity {
    
    @Column(nullable = false)
    private String codigoArea;

    @Column(nullable = false)
    private String numero;

    public Telefone () {
        
    }

    public Telefone (TelefoneDTO telefoneDTO) {

        this.codigoArea = telefoneDTO.codigoArea();
        this.numero = telefoneDTO.numero();
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
