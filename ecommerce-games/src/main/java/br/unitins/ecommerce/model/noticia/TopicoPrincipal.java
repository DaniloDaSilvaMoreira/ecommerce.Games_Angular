package br.unitins.ecommerce.model.noticia;

public enum TopicoPrincipal {
    
    EVENTO(1, "Evento"),
    LANCAMENTO(2, "Lançamento"),
    ANALISE_JOGO(3, "Análise de jogo"),
    ATUALIZACAO(4, "Atualização");

    private int id;
    private String label;

    TopicoPrincipal (int id, String label) {

        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TopicoPrincipal valueOf (Integer id) throws IllegalArgumentException {

        if (id == null)
            return null;

        for (TopicoPrincipal topicoPrincipal : TopicoPrincipal.values()) {
            
            if (id == topicoPrincipal.id)
                return topicoPrincipal;
        }

        throw new IllegalArgumentException("Número fora das opções disponíveis");
    }
}
