import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Plataforma } from 'src/app/models/plataforma.model';
import { PlataformaService } from 'src/app/services/plataforma.service';

@Component({
  selector: 'app-plataforma-list',
  templateUrl: './plataforma-list.component.html',
  styleUrls: ['./plataforma-list.component.css']
})
export class PlataformaListComponent implements OnInit {

  tableColumns: string[] = ['id-column', 'nome-column', 'descricao-column','ano-lancamento-column','fabricante-column','acoes-column'];
  plataformas: Plataforma[] = [];

  constructor(private plataformaService: PlataformaService) {}

  ngOnInit(): void {
    this.plataformaService.findAll().subscribe(data => {

      this.plataformas = data;
    });
  }

  excluir(plataforma: Plataforma) {

      if (plataforma.id != null) {

        this.plataformaService.delete(plataforma).subscribe({
          next: (plataformaCadastrada) => {
            this.ngOnInit();
          },
          error: (err) => {
            console.log('Erro ao excluir' + JSON.stringify(err));
          }
        })
      }
  }
}
