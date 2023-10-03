import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Developer } from 'src/app/models/developer.model';
import { DeveloperService } from 'src/app/services/developer.service';

@Component({
  selector: 'app-developer-list',
  templateUrl: './developer-list.component.html',
  styleUrls: ['./developer-list.component.css']
})
export class DeveloperListComponent implements OnInit {

  tableColumns: string[] = ['id-column', 'nome-column', 'ano-fundacao-column', 'acoes-column'];
  developers: Developer[] = [];

  constructor(private developerService: DeveloperService) {}

  ngOnInit(): void {
    this.developerService.findAll().subscribe(data => {

      this.developers = data;
    });
  }

  excluir(developer: Developer) {

      if (developer.id != null) {

        this.developerService.delete(developer).subscribe({
          next: (developerCadastrado) => {
            this.ngOnInit();
          },
          error: (err) => {
            console.log('Erro ao excluir' + JSON.stringify(err));
          }
        })
      }
  }
}
