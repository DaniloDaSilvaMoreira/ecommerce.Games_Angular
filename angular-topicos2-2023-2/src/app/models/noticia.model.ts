import { TopicoPrincipal } from "./topico-principal.enum";

export class Noticia {

  id!: number;
  titulo!: string;
  conteudo!: string;
  dataPublicacao!: Date;
  autor!: string;
  topicoPrincipal !: TopicoPrincipal;
}
