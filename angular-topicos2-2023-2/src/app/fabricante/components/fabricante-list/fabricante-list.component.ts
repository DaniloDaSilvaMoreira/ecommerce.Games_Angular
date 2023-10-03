import { Component, OnInit } from '@angular/core';
import { Fabricante } from 'src/app/models/fabricante.model';
import { FabricanteService } from 'src/app/services/fabricante.service';

@Component({
  selector: 'app-fabricante-list',
  templateUrl: './fabricante-list.component.html',
  styleUrls: ['./fabricante-list.component.css']
})
export class FabricanteListComponent implements OnInit {

  tableColumns: string[] = ['id-column', 'nome-column', 'ano-fundacao-column', 'acoes-column'];
  fabricantes: Fabricante[] = [];

  constructor(private fabricanteService: FabricanteService) {}

  ngOnInit(): void {
    this.fabricanteService.findAll().subscribe(data => {

      this.fabricantes = data;
    });
  }

  excluir(fabricante: Fabricante) {

      if (fabricante.id != null) {

        this.fabricanteService.delete(fabricante).subscribe({
          next: (fabricanteCadastrado) => {
            this.ngOnInit();
          },
          error: (err) => {
            console.log('Erro ao excluir' + JSON.stringify(err));
          }
        })
      }
  }
}
