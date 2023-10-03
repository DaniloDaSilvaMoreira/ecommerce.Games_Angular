import { Component, OnInit } from '@angular/core';
import { Genero } from 'src/app/models/genero.model';
import { GeneroService } from 'src/app/services/genero.service';

@Component({
  selector: 'app-genero-list',
  templateUrl: './genero-list.component.html',
  styleUrls: ['./genero-list.component.css']
})
export class GeneroListComponent implements OnInit {

  tableColumns: string[] = ['id-column', 'nome-column', 'acoes-column'];
  generos: Genero[] = [];

  constructor(private generoService: GeneroService) {}

  ngOnInit(): void {
    this.generoService.findAll().subscribe(data => {
      this.generos = data;
    });
  }

  excluir(genero: Genero) {

      if (genero.id != null) {

        this.generoService.delete(genero).subscribe({
          next: (generoCadastrado) => {
            this.ngOnInit();
          },
          error: (err) => {
            console.log('Erro ao excluir' + JSON.stringify(err));
          }
        })
      }
  }
}
