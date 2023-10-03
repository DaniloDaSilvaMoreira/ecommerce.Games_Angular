package br.unitins.ecommerce.converterjpa;

import br.unitins.ecommerce.model.noticia.TopicoPrincipal;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TopicoPrincipalConverter implements AttributeConverter<TopicoPrincipal, String> {

    @Override
    public String convertToDatabaseColumn(TopicoPrincipal topicoPrincipal) {
        
        return topicoPrincipal == null ? null : topicoPrincipal.getLabel();
    }

    @Override
    public TopicoPrincipal convertToEntityAttribute(String label) {
        
        return label == null ? null : TopicoPrincipal.valueOf(label.toUpperCase());
    }
    
}
