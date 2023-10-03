import { Perfil } from "./perfil.enum";
import { Telefone } from "./telefone.model";

export class Usuario {

  id!: number;
  nome!: string;
  cpf!: string;
  email!: string;
  login!: string;
  senha!: string;
  perfil!: Perfil;
  telefones!: Telefone[];
}
