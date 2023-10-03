package br.unitins.ecommerce.service.noticia;

import java.util.List;

import br.unitins.ecommerce.dto.NoticiaDTO;
import br.unitins.ecommerce.model.noticia.Noticia;

public interface NoticiaService {
    
    List<Noticia> getAll();
    
    Noticia getById(Long id);

    Noticia insert(NoticiaDTO noticiaDTO);

    Noticia update(Long id, NoticiaDTO noticiaDTO);

    void delete(Long id);
}
