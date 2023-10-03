import { Component, OnInit } from '@angular/core';
import { Cidade } from 'src/app/models/cidade.model';
import { CidadeService } from 'src/app/services/cidade.service';

@Component({
  selector: 'app-cidade-list',
  templateUrl: './cidade-list.component.html',
  styleUrls: ['./cidade-list.component.css']
})
export class CidadeListComponent implements OnInit {

  tableColumns: string[] = ['id-column', 'nome-column', 'estado-column', 'acoes-column'];
  cidades: Cidade[] = [];

  constructor(private cidadeService: CidadeService) {}

  ngOnInit(): void {
    this.cidadeService.findAll().subscribe(data => {
      this.cidades = data;
    });
  }

  excluir(cidade: Cidade) {

    if (cidade.id != null) {

      this.cidadeService.delete(cidade).subscribe({
        next: (cidadeCadastrado) => {
          this.ngOnInit();
        },
        error: (err) => {
          console.log('Erro ao excluir' + JSON.stringify(err));
        }
      })
    }
}
}
